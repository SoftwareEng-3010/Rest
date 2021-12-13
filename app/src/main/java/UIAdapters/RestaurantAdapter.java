package UIAdapters;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.exercise_5.R;

import java.util.ArrayList;
import java.util.List;

import BusinessEntities.Restaurant;
import DataAccessLayer.RestDB;
import UI.BranchesViewActivity;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

    private static final String TAG = "RestaurantAdapter";
    private Context context;
    private int resource;
    private List<Restaurant> restaurants;

    public RestaurantAdapter(@NonNull Context context, int resource, List<Restaurant> restaurants) {
        super(context, resource, restaurants);
        this.restaurants = restaurants;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Restaurant restaurant = restaurants.get(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }
        // Lookup view for data population
        TextView rName = convertView.findViewById(R.id.restaurantNameTextView);

        Button moveToBranches = convertView.findViewById(R.id.restaurantBranchesButton);
        moveToBranches.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * move to BranchesActivity
             */
            public void onClick(View v) {

                ListView parentView = (ListView) v.getParent().getParent().getParent();
                int index = parentView.indexOfChild((View) v.getParent().getParent());
                Intent moveToBranchesActivity =
                        new Intent(getContext(), BranchesViewActivity.class);
                moveToBranchesActivity.putExtra("restInd", index);
                getContext().startActivity(moveToBranchesActivity);
            }
        });


        // Populate the data into the template view using the data object
        rName.setText(restaurant.getName());

        // Return the completed view to render on screen
        return convertView;
    }

}
