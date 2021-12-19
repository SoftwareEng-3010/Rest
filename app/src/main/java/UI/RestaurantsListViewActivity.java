package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.exercise_5.R;

import java.util.List;

import BusinessEntities.Restaurant;
import DataAccessLayer.OnDataReceived;
import DataAccessLayer.RemoteRestDB;
import UIAdapters.RestaurantAdapter;

public class RestaurantsListViewActivity extends AppCompatActivity {

    private RemoteRestDB rdb;
    private ListView listView;
    private List<Restaurant> restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_scrollview);
        rdb = RemoteRestDB.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        listView = (ListView) findViewById(R.id.restaurantListView);


        rdb.getRestaurants(new OnDataReceived() {
            @Override
            public void onObjectReturnedFromDB(Object obj) {
                restaurants = (List<Restaurant>) obj;

                ArrayAdapter<Restaurant> adapter = new RestaurantAdapter(
                        getApplicationContext(),
                        R.layout.item_restaurant,
                        restaurants
                        );

                listView.setAdapter(adapter);
            }
        });
    }

}