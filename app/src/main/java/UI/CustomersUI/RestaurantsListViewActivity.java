package UI.CustomersUI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.exercise_5.R;

import java.util.List;

import API.Database.DatabaseRequestCallback;
import BusinessEntities.Restaurant;
import DataAccessLayer.RestDB;
import UIAdapters.RestaurantArrayAdapter;

public class RestaurantsListViewActivity extends AppCompatActivity {

    private RestDB rdb;
    private ListView listView;
    private List<Restaurant> restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_scrollview);
        rdb = RestDB.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        listView = (ListView) findViewById(R.id.restaurantListView);

        setUpAdapter();
    }

    private void setUpAdapter() {
        rdb.getRestaurants(new DatabaseRequestCallback() {
            @Override
            public void onObjectReturnedFromDB(Object obj) {

                if (obj == null) return; // An error occurred

                restaurants = (List<Restaurant>) obj;

                ArrayAdapter<Restaurant> adapter = new RestaurantArrayAdapter(
                        RestaurantsListViewActivity.this,
                        R.layout.layout_restaurant_item,
                        restaurants
                );

                listView.setAdapter(adapter);
            }
        });
    }

}