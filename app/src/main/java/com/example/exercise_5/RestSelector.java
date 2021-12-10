package com.example.exercise_5;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RestSelector extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_selector);

        String userName = getIntent().getStringExtra("UserEmail");
//        String userEmail = getIntent().getStringExtra("UserEmail");

        Toast.makeText(this, "Welcome, " + userName, Toast.LENGTH_LONG).show();
    }
}
