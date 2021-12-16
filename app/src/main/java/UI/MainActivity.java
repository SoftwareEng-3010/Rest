package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import DataAccessLayer.RemoteRestDB;
import DataAccessLayer.RestDB;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate()");
        RemoteRestDB.getInstance(); // initialize db class
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart()");
        Intent intent  = new Intent(this, LoginActivity.class);
        startActivity(intent);
//        finish();
    }
}