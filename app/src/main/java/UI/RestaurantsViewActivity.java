package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.exercise_5.R;

import java.util.List;

import BusinessEntities.Restaurant;
import DataAccessLayer.RestDB;

public class RestaurantsViewActivity extends AppCompatActivity {

    private RestDB rdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_view_activity);
        rdb = RestDB.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        ListView listView = (ListView) findViewById(R.id.restaurantListView);

        ArrayAdapter<Restaurant> adapter = new RestaurantAdapter(
                this,
                R.layout.item_restaurant,
                rdb.getRestaurants()
        );

        listView.setAdapter(adapter);




        // Construct the data source
//        List<Restaurant> restaurants = rdb.getRestaurants();
//        // Create the adapter to convert the array to views
//        RestaurantAdapter adapter = new RestaurantAdapter(
//                this,
//                R.layout.item_restaurant,
//                restaurants);
//        // Attach the adapter to a ListView
//        ListView listView = findViewById(R.id.restaurantsListView);
//        listView.setAdapter(adapter);
//
//        for (Restaurant r : restaurants) {
//            adapter.add(r);
//        }
    }

}