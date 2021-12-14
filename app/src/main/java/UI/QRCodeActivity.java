package UI;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.budiyev.android.codescanner.ScanMode;
import com.example.exercise_5.R;
import com.google.zxing.Result;

import BusinessLogic.QRReader;
//import BusinessLogic.QRReader;

public class QRCodeActivity extends AppCompatActivity {

    private final String TAG = "QRCodeActivity";
    private final String SELECTED_RESTAURANT_INDEX  = "restaurant_index";
    private final String SELECTED_BRANCH_INDEX = "branch_index";
    private final String SELECTED_TABLE_INDEX = "table_index";

    private final String PERMISSION_PROMPT = "You will need to allow Camera permissions to " +
            "scan a QR code at the restaurant you are visiting";

    private CodeScanner qrScanner;
    private CodeScannerView qrScannerView;
    private TextView text;
    private final int REQUEST_PERMISSION_CODE = 210;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode_activity);
        Log.e(TAG, "onCreate(QR)");

        // Get the CodeScannerView brought from `com.budiyev`
        qrScannerView = (CodeScannerView)findViewById(R.id.scanner_view);
        text = (TextView)findViewById(R.id.qrTextView);

        qrScanner = new CodeScanner(this, qrScannerView);
        qrScanner.setCamera(CodeScanner.CAMERA_BACK);
        qrScanner.setScanMode(ScanMode.CONTINUOUS);

        setQRCodeCaptureCallbackMethod();
        setQRCodeErrorCallbackMethod();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "Started QRCodeActivity (onStart())");
        // Check whether your app is running on a device that has a camera hardware feature.
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_ANY)) {
            // Continue with the part of your app's workflow that requires a
            // front-facing camera.
            Log.d(TAG, "PackageManager: There is a camera available");
        }
        else {
            // Gracefully degrade your app experience.
            Log.e(TAG, "PackageManager: There are NO cameras available to open");
        }

        if (checkPermission()) {
            Log.d(TAG, "PERMISSION GRANTED");
            // If the user have granted camera permission - start scanning QRCodes
            qrScanner.startPreview();
        }
        else {
            Log.e(TAG, "PERMISSION DECLINED");
            // If the user have not yet granted permissions
            // or have previously declined permissions - ask the user for permissions.
            requestPermission();
        }
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Camera permission granted", Toast.LENGTH_SHORT).show();
                    // If camera permission is granted - start camera preview
                    qrScanner.startPreview();
                } else {
                    Toast.makeText(getApplicationContext(), "Camera permission denied", Toast.LENGTH_SHORT).show();
                    // if user sdk version >= 23
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel(
                                    PERMISSION_PROMPT,

                                    // onOKListener action:
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                Log.d(TAG, "which = " + which);
                                                requestPermission();
                                            }
                                        }
                                    },

                                    // onCancelListener action:
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int which) {
                                            Log.d(TAG, "which = " + which);
                                            finishActivity(-1);
                                        }
                                    });
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener
                                                   , DialogInterface.OnClickListener cancelListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", cancelListener)
                .create()
                .show();
    }


    /**
     * Set up a `CallbackHandler` for incoming QRCodes
     * scanned by the user camera.
     */
    private void setQRCodeCaptureCallbackMethod() {
        qrScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(QRCodeActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "QR Code found: " + result.getText());
                        try {
                            // QRReader will return an array of the relevant data
                            // to load a `Branch` menu, which is a List<Item> object
                            int[] qrResult = QRReader.readQRResult(result);

                            Intent moveToBranchDisplay = new Intent(QRCodeActivity.this,
                                    BranchViewActivity.class);

                            // Prepare data from next activity
                            moveToBranchDisplay.putExtra(SELECTED_RESTAURANT_INDEX, qrResult[0]);
                            moveToBranchDisplay.putExtra(SELECTED_BRANCH_INDEX, qrResult[1]);
                            moveToBranchDisplay.putExtra(SELECTED_TABLE_INDEX, qrResult[2]);

                            // Stop camera and move to BranchDisplay
                            qrScanner.stopPreview();
                            qrScanner.releaseResources();
                            startActivity(moveToBranchDisplay);
                            finish(); // activity can be finished
                        }
                        catch (Exception e) {
                            Log.e(TAG, "The QRCode is not in the correct format or something");
                            Log.e(TAG, e.getMessage());
                            qrScanner.startPreview();
//                            Toast.makeText(QRCodeActivity.this, "Invalid QRCode!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    /**
     * Set up an `ErrorCallback` action for handling
     * scanner errors.
     */
    private void setQRCodeErrorCallbackMethod() {
        qrScanner.setErrorCallback(new ErrorCallback() {
            @Override
            public void onError(@NonNull Exception error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        qrScanner.stopPreview();
        qrScanner.releaseResources(); // Release the camera connected to the Scanner object.
        finish();          // Stop this activity. Even on hold it seems to slow things down.
    }

    @Override
    protected void onResume() {
        super.onResume();
//        qrScanner.startPreview();
    }
}
