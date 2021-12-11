package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.exercise_5.R;

import java.util.ArrayList;
import java.util.List;

import BusinessEntities.Restaurant;
import DataAccessLayer.RestDB;

public class ViewRestActivity extends AppCompatActivity {

    RestDB rdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rest);
        rdb = RestDB.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        List<Restaurant> restaurant = rdb.getRestaurants();
    }
}