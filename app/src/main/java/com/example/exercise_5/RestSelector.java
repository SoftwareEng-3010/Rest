package com.example.exercise_5;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import BusinessEntities.Restaurant;
import DataAccessLayer.RestDB;
import UI.ViewRestActivity;

public class RestSelector extends AppCompatActivity {
    private RestDB restDB;
    private List<Restaurant> restaurants;

    private final String TAG = "RestSelector";

    private Button qrBtn;
    private Button listBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_selector);

        String userName = getIntent().getStringExtra("UserEmail");
        Toast.makeText(this, "Welcome, " + userName, Toast.LENGTH_SHORT).show();

        qrBtn = (Button)findViewById(R.id.qr_button);
        listBtn = (Button)findViewById(R.id.list_button);

        restDB = RestDB.getInstance();
        restaurants = restDB.getRestaurants();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, Integer.toString(restaurants.size()));
        initListeners();
    }

    private void initListeners(){

        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewRestIntent = new Intent(RestSelector.this, ViewRestActivity.class);
                startActivity(viewRestIntent);
            }
        });
    }
}
