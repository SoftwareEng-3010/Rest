package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.exercise_5.R;

import java.util.ArrayList;

import BusinessEntities.Restaurant;
import DataAccessLayer.RestDB;

public class ViewRestActivity extends AppCompatActivity {

    RestDB rdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rest);
        rdb = new RestDB();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        rdb.getRestaurants(restaurants);
        for(Restaurant r : restaurants){
            Toast.makeText(ViewRestActivity.this, r.getName(), Toast.LENGTH_SHORT).show();
        }
    }
}