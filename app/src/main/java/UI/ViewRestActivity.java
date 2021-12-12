package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.exercise_5.R;

import java.util.ArrayList;
import java.util.List;

import BusinessEntities.Restaurant;
import DataAccessLayer.RestDB;

public class ViewRestActivity<adapter> extends AppCompatActivity {

    private RestDB rdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rest);
        rdb = RestDB.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Construct the data source
        List<Restaurant> restaurants = rdb.getRestaurants();
        // Create the adapter to convert the array to views
        RestViewAdapter adapter = new RestViewAdapter(this, (ArrayList<Restaurant>) restaurants);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.restListView);
        listView.setAdapter(adapter);

        for (Restaurant r : restaurants) {
            adapter.add(r);
        }
    }

}