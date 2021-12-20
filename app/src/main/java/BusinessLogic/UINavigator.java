package BusinessLogic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Map;

public class UINavigator {
    private static final String TAG = "UINavigator";
    private UINavigator() {}


    public static void moveToActivity(Activity activity, Class<?> tClass) {
        Intent intent = new Intent(activity, tClass);
        activity.startActivity(intent);
    }

    public static void moveToActivityAndFinish(Activity activity, Class<?> tClass) {
        moveToActivity(activity, tClass);
        activity.finish();
    }

    public static void moveToActivityWithStringExtras(Activity activity, Class<?> tClass,
                                                String[] keys, String[] values) {
        if (keys == null || values == null) {
            Log.e(TAG, "No keys or values were passed");
            return;
        }
        if (keys.length != values.length) {
            Log.e(TAG, "There are " + keys.length + " keys, and " + values.length + " values");
            return;
        }

        Intent intent = new Intent(activity, tClass);

        for (int i = 0; i < keys.length; ++i) {
            intent.putExtra(keys[i], values[i]);
        }
    }

    public static void moveToActivityWithExtras(Activity activity, Class<?> tClass,
                                                Map<String, Object> extras) {

        Intent intent = new Intent(activity, tClass);

        for (Map.Entry<String, Object> entry : extras.entrySet()) {
            intent.putExtra(entry.getKey(), (Bundle) entry.getValue());
        }
        Log.e(TAG, intent.toString());
    }
}
