package BusinessLogic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import DataAccessLayer.RestDB;
import UI.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RestDB.getInstance(); // initialize db class
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent  = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}