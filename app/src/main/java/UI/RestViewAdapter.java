package UI;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.exercise_5.R;

import java.util.ArrayList;

import BusinessEntities.Restaurant;

public class RestViewAdapter extends ArrayAdapter<Restaurant> {

    public RestViewAdapter(Context context, ArrayList<Restaurant> restaurants) {
        super(context, 0, restaurants);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Restaurant restaurant = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_restaurant, parent, false);
        }
        // Lookup view for data population
        TextView rName = (TextView) convertView.findViewById(R.id.restaurantName);
//        TextView rBranches = (TextView) convertView.findViewById(R.id.restaurantNumberOfBranches);
        // Populate the data into the template view using the data object
        rName.setText(restaurant.getName());
//        rBranches.setText(restaurant.getBranches());

        // Return the completed view to render on screen
        return convertView;
    }

}
