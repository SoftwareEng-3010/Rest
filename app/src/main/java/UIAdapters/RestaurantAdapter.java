package UIAdapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.exercise_5.R;

import java.util.List;

import BusinessEntities.Restaurant;
import UI.BranchesListViewActivity;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

    private static final String TAG = "RestaurantAdapter";
    private Context context;
    private int resource;
    private List<Restaurant> restaurantNames;

    public RestaurantAdapter(@NonNull Context context, int resource, List<Restaurant> restaurants) {
        super(context, resource, restaurants);
        this.restaurantNames = restaurants;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Restaurant restaurant = restaurantNames.get(position);
        String restaurantName = restaurant.getName();
//        String restaurantId = restaurant.getId();

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        // Lookup view for data population
        Button moveToBranchesBtn = convertView.findViewById(R.id.restaurantBranchesButton);
        moveToBranchesBtn.setText(restaurantName);
        moveToBranchesBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * move to BranchesActivity
             */
            public void onClick(View v) {

                ListView parentView = (ListView) v.getParent().getParent().getParent();
                int index = parentView.indexOfChild((View) v.getParent().getParent());
                Intent moveToBranchesActivity =
                        new Intent(getContext(), BranchesListViewActivity.class);
                String restName = restaurantNames.get(index).getName();
                moveToBranchesActivity.putExtra("restName", restName);
                getContext().startActivity(moveToBranchesActivity);
            }
        });


        // Return the completed view to render on screen
        return convertView;
    }

}
