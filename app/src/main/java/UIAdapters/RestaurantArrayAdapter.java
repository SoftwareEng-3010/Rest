package UIAdapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.exercise_5.R;

import java.util.List;

import BusinessEntities.Restaurant;
import UI.CustomersUI.BranchesListViewActivity;
import UI.CustomersUI.IActionListener;

public class RestaurantArrayAdapter extends ArrayAdapter<Restaurant> {

    private final String TAG = "RestaurantArrayAdapter";
    private Context context;
    private int resource;
    private List<Restaurant> restaurants;
    private IActionListener listener;

    public RestaurantArrayAdapter(IActionListener listener, @NonNull Context context, int resource, List<Restaurant> restaurants) {
        super(context, resource, restaurants);
        this.restaurants = restaurants;
        this.listener = listener;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Restaurant restaurant = restaurants.get(position);
        String restaurantName = restaurant.getName();

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        // Lookup view for data population
//        TextView restName = convertView.findViewById(R.id.setRestaurantName);
//        restName.setText(restaurantName);

        Button moveToBranchesBtn = convertView.findViewById(R.id.restaurantBranchesButton);
        moveToBranchesBtn.setText(restaurantName);

        moveToBranchesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAction(view);
            }
        });
//        moveToBranchesBtn.setOnClickListener();
//        moveToBranchesBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            /**
//             * move to BranchesActivity
//             */
//            public void onClick(View v) {
//
////                ListView parentView = (ListView) v.getParent().getParent();
////                int index = parentView.indexOfChild((View) v.getParent());
////                Intent branchListViewActivity =
////                        new Intent(getContext(), BranchesListViewActivity.class);
////                String restID = restaurants.get(index).getDocId();
////                branchListViewActivity.putExtra("rest_id", restID);
////                getContext().startActivity(branchListViewActivity);
//
//            }
//        });


        // Return the completed view to render on screen
        return convertView;
    }

}
