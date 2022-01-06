package UI.CustomersUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.exercise_5.R;

import java.util.List;

import API.Database.DatabaseRequestCallback;
import BusinessEntities.Restaurant;
import DataAccessLayer.RestDB;
import UIAdapters.RestaurantArrayAdapter;

public class RestaurantsListViewActivity extends AppCompatActivity implements IActionListener {

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
                        RestaurantsListViewActivity.this,
                        R.layout.layout_restaurant_item,
                        restaurants
                );

                listView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onAction(View v) {
        ListView parentView = (ListView) v.getParent().getParent();
        int index = parentView.indexOfChild((View) v.getParent());
        Intent branchListViewActivity =
        new Intent(this, BranchesListViewActivity.class);
        String restID = restaurants.get(index).getDocId();
        branchListViewActivity.putExtra("rest_id", restID);
        startActivity(branchListViewActivity);
        finish();
    }
}