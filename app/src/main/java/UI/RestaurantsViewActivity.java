package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.exercise_5.R;

import BusinessEntities.Restaurant;
import DataAccessLayer.RestDB;
import UIAdapters.RestaurantAdapter;

public class RestaurantsViewActivity extends AppCompatActivity {

    private RestDB rdb;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_view_activity);
        rdb = RestDB.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        listView = (ListView) findViewById(R.id.restaurantListView);

        ArrayAdapter<Restaurant> adapter = new RestaurantAdapter(
                this,
                R.layout.item_restaurant,
                rdb.getRestaurants()
        );

        listView.setAdapter(adapter);
    }

}