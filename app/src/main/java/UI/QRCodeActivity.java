package UI;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
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

public class QRCodeActivity extends AppCompatActivity {

    private final String TAG = "QRCodeActivity";
    private CodeScanner qrScanner;
    private CodeScannerView qrScannerView;
    private TextView text;
    private final int REQUEST_PERMISSION_CODE = 210;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode_activity);

        qrScannerView = (CodeScannerView)findViewById(R.id.scanner_view);
        text = (TextView)findViewById(R.id.qrTextView);

        qrScanner = new CodeScanner(this, qrScannerView);
        qrScanner.setCamera(CodeScanner.CAMERA_BACK);
        qrScanner.setScanMode(ScanMode.CONTINUOUS);

        setQRCodeCaptureCallbackMethod(qrScanner);
        setQRCodeErrorCallbackMethod(qrScanner);

        // Check whether your app is running on a device that has a camera hardware feature.
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_ANY)) {
            // Continue with the part of your app's workflow that requires a
            // front-facing camera.
            Log.d(TAG, "PackageManager: There is a camera available");
        } else {
            // Gracefully degrade your app experience.
            Log.e(TAG, "PackageManager: There are NO cameras available to open");
        }

        if (checkPermission()) {
            Log.d(TAG, "PERMISSION GRANTED");
            qrScanner.startPreview();
        } else {
            Log.e(TAG, "PERMISSION DECLINED");
            requestPermission();
        }

//        // Check whether the user have already granted camera permissions:
//        if (ContextCompat.checkSelfPermission(this, CAMERA_SERVICE)
//                ==  PackageManager.PERMISSION_GRANTED) {
//            Log.d(TAG, "Camera permission GRANTED");
//
//        } else {
//            Log.e(TAG, "Camera permission DENIED");
////            finishActivity(0);
//        }


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
                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                    // main logic
                    qrScanner.startPreview();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    // if user sdk version >= 23
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You will need to allow Camera permissions to " +
                                            "scan a QR code at the restaurant you are visiting",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                Log.d(TAG, "which = " + which);
                                                requestPermission();
                                            }
                                        }
                                    }, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int which) {
                                            Log.d(TAG, "which = " + which);
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

    private void setQRCodeErrorCallbackMethod(CodeScanner qrScanner) {
        qrScanner.setErrorCallback(new ErrorCallback() {
            @Override
            public void onError(@NonNull Exception error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    private void setQRCodeCaptureCallbackMethod(CodeScanner qrScanner) {
        qrScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(QRCodeActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
