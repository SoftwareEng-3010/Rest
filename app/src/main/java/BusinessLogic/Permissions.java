package BusinessLogic;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permissions {

    private static int counter = 0;

    public static boolean isPermissionGranted(String permission, Activity activity) {
        return ContextCompat.checkSelfPermission(activity, permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermission(String permission, int requestCode, Activity activity) {

        if (permission.equals(Manifest.permission.CAMERA)) {
            if (!activity.getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_ANY)) {
                return;
            }
        }

        // if user sdk version >= 23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!isPermissionGranted(permission, activity)) {
                if (counter++ < 2) {
                    // prevent a Rationale message loop
                    ActivityCompat.requestPermissions(activity,
                            new String[]{permission},
                            requestCode);
                }
            }
        }
    }



}
