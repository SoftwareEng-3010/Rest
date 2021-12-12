package com.example.exercise_5;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import BusinessEntities.Restaurant;
import DataAccessLayer.RestDB;

public class RestSelector extends AppCompatActivity {
    private RestDB restDB;
    private List<Restaurant> restaurants;

    private final String TAG = "RestSelector";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_selector);

        String userEmail = getIntent().getStringExtra("UserEmail");
        Toast.makeText(this, "Welcome, " + userEmail, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, Integer.toString(RestDB.getInstance().getRestaurants().size()));

//        restDB = RestDB.getInstance();
//        restaurants = restDB.getRestaurants();
        for (Restaurant r : RestDB.getInstance().getRestaurants())
            Log.d(TAG, r.toString());
    }
}
