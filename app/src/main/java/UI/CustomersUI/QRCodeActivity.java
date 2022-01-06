package UI.CustomersUI;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.budiyev.android.codescanner.ScanMode;
import com.example.exercise_5.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.Result;

import API.Constants.Constants;
import API.Views.SwipeGestureListener;
import BusinessEntities.QRCode;
import BusinessLogic.QRReadHandler;
import BusinessLogic.Permissions;
import UI.LoginUI.LoginActivity;

public class QRCodeActivity extends AppCompatActivity implements SwipeGestureListener {

    private final String TAG = "QRCodeActivity";

    private final String PERMISSION_PROMPT = "You will need to allow Camera permissions to " +
            "scan a QR code at the restaurant you are visiting";

    private CodeScannerView qrScannerView; // QRScanner view reference
    private CodeScanner qrScanner; // QRScanner object reference

    private Button showListBtn;    // Manual selection button
    private Button logoutBtn;    // Manual selection button
    private Button managerModeBtn;    // Manual selection button

    private int userType;

    private final int REQUEST_PERMISSION_CODE = 210; // Any permission code would work

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        Log.e(TAG, "onCreate(QR)");

        // Get the CodeScannerView brought from `com.budiyev`
        qrScannerView = (CodeScannerView) findViewById(R.id.scanner_view);

        qrScanner = new CodeScanner(this, qrScannerView);
        qrScanner.setCamera(CodeScanner.CAMERA_BACK);
        qrScanner.setScanMode(ScanMode.CONTINUOUS);

        setQRCodeCaptureCallbackMethod();
        setQRCodeErrorCallbackMethod();

        /* Add an additional button for restaurant managers to move to their Management UI flow*/
        // managersButton = (Button) findViewById(R.id.someButtonForManagers);
        initListeners();

//        if (userType == Constants.USER_TYPE_BRANCH_MANAGER) {
//
//        }
    }

    private void initListeners(){

        // References to buttons
        showListBtn = (Button)findViewById(R.id.showListButton);
        logoutBtn = (Button)findViewById(R.id.button_log_out);
        managerModeBtn = (Button)findViewById(R.id.button_move_to_management);

        userType = getIntent().getIntExtra("user_type", -1);

        // TODO: REMOVE for presentation
        logoutBtn.setVisibility(View.VISIBLE);
        if (userType == 1) {
            managerModeBtn.setVisibility(View.VISIBLE);
            logoutBtn.setVisibility(View.VISIBLE);
        }

        showListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent moveToRestActivity =
                        new Intent(QRCodeActivity.this, RestaurantsListViewActivity.class);
                startActivity(moveToRestActivity);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent moveToLoginActivity =
                        new Intent(QRCodeActivity.this, LoginActivity.class);
                startActivity(moveToLoginActivity);
                finish();
            }
        });

        managerModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Only finish the activity because when logged in as a manager,
                // this activity is above ManagementActivity in UI Stack.
                moveToManagementUI();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "Started QRCodeActivity (onStart())");

        Permissions.requestCameraPermission(
                REQUEST_PERMISSION_CODE
                , this);
        if (Permissions.isPermissionGranted(Manifest.permission.CAMERA, this)) {
            qrScanner.startPreview();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (REQUEST_PERMISSION_CODE == requestCode) {
            // The request code we sent
            if (Permissions.isPermissionGranted(permissions[0], this)) {
                Toast.makeText(getApplicationContext(), "Camera permission granted", Toast.LENGTH_SHORT).show();
                // If camera permission is granted - start camera preview
                qrScanner.startPreview();
            } else {
                Toast.makeText(getApplicationContext(), "Camera permission denied", Toast.LENGTH_SHORT).show();
                showPermissionRationaleAlertDialog(PERMISSION_PROMPT);
            }
        }
    }

    private void showPermissionRationaleAlertDialog(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Permissions.requestPermission(
                                Manifest.permission.CAMERA, REQUEST_PERMISSION_CODE,
                                QRCodeActivity.this);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Log.d(TAG, "Cancel pressed");
                    }
                })
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
                        Log.d(TAG, "QR Code found: " + result.getText());
                        qrScanner.stopPreview();

                        try {

                            // Validating QRCode and move to BranchView

                            QRCode scannedQR = QRReadHandler.readFromResult(result);

                            // Release camera resource
                            qrScanner.releaseResources();

                            // Move to next activity
                            moveToBranchViewActivity(scannedQR);
                        }
                        catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                            qrScanner.startPreview();
                        }
                    }
                });
            }
        });
    }

    private void moveToBranchViewActivity(QRCode qr) {
        Intent moveToBranchView = new Intent(this, BranchView.class);

        // Add extra data to next activity:
        moveToBranchView.putExtra(Constants.KEY_RESTAURANT_ID, qr.getRestaurantId());
        moveToBranchView.putExtra(Constants.KEY_BRANCH_ID, qr.getBranchId());
        moveToBranchView.putExtra(Constants.KEY_TABLE_NUMBER, qr.getTableNumber());

        startActivity(moveToBranchView);
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
        Log.e(TAG, "onStop()");
        qrScanner.stopPreview();
        qrScanner.releaseResources(); // Release the camera connected to the Scanner object.
    }

    @Override
    public void onSwipeLeft() {

    }

    @Override
    public void onSwipeRight() {
        moveToManagementUI();
    }

    public void moveToManagementUI() {
//        Intent managementActivity = new Intent(this, ManagementActivity.class);
//        managementActivity.putExtra("user_type", userType);
//        startActivity(managementActivity);
        finish();
    }
}
