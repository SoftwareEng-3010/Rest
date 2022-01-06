package DataAccessLayer;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionManager {

    private final String TAG = "QRCodeActivity";
    private Activity activity;
//    private final int REQUEST_PERMISSION_CODE = 210;

    public PermissionManager(Activity activity) {
        this.activity = activity;
    }

    public boolean hasPermission(String permission) {
        if (ContextCompat.checkSelfPermission(activity, permission)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    public void requestPermission(int REQUEST_PERMISSION_CODE, String permission) {

        ActivityCompat.requestPermissions(activity,
                new String[]{permission},
                REQUEST_PERMISSION_CODE);
    }

    public boolean hasSystemFeature(String feature) {
        if (activity.getPackageManager().hasSystemFeature(
                feature)) {
            // Continue with the part of your app's workflow that requires a
            // front-facing camera.
            return true;
        }

        // Gracefully degrade your app experience.
        return false;

    }


}
