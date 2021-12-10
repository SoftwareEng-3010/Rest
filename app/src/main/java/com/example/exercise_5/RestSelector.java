package com.example.exercise_5;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import BusinessEntities.Restaurant;
import DataAccessLayer.RestDB;

public class RestSelector extends AppCompatActivity {
    RestDB restDB = new RestDB();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_selector);

        String userName = getIntent().getStringExtra("UserEmail");
        Toast.makeText(this, "Welcome, " + userName, Toast.LENGTH_LONG).show();

        restDB = new RestDB();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        restDB.getRestaurants(restaurants);
        Log.d("RestSelector", Integer.toString(restaurants.size()));
        for(Restaurant restaurant : restaurants){
            Log.d("RestSelector", restaurant.getName());
        }
    }
}
