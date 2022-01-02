package UI.DataActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.exercise_5.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import BusinessEntities.Address;
import BusinessEntities.Branch;
import BusinessEntities.Item;
import BusinessEntities.Menu;
import BusinessEntities.Restaurant;
import BusinessEntities.Table;
import UI.DataActivity.Controller.DataEditViewController;
import UI.DataActivity.Controller.DataViewController;
import UI.DataActivity.View.CreateBranchFragment;
import UI.DataActivity.View.CreateMenuFragment;
import UI.DataActivity.View.CreateRestaurantFragment;
import UI.DataActivity.View.CreateTablesFragment;
import UI.DataActivity.View.DataEditView;
import UI.RestaurantManagementUI.ManagementMainActivity;

public class DataActivity extends AppCompatActivity implements DataEditView {

    // Business entities to be constructed
//    private Restaurant restaurant;
//    private Branch branch;
//    private Menu menu;

    private final String TAG = "DataActivity";
    // A placeholder for activity fragments
    private FrameLayout frameLayout;
    private ProgressBar progressBar;


    private DataViewController viewController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar_data_activity);

        viewController = new DataEditViewController(this);

        moveToFragment(new CreateRestaurantFragment(viewController), "CreateRestaurantFragment");
    }

    @Override
    public void onRestaurantEditFinished(boolean isSuccessful, String restaurantName) {

        if (isSuccessful) {
            moveToFragment(new CreateBranchFragment(viewController), "EditRestaurantFragment");
        }
        else {
            Log.e(TAG, "Something went wrong....");
        }
    }

    @Override
    public void onBranchEditFinished(boolean isSuccessful, Address address, boolean isKosher) {
        if (isSuccessful) {
            moveToFragment(new CreateMenuFragment(viewController), "EditBranchFragment");
        }
        else {
            Log.e(TAG, "Something went wrong....");
        }
    }

    @Override
    public void onMenuEditFinished(boolean isSuccessful, List<Item> menuItems) {
        if (isSuccessful) {
            moveToFragment(new CreateTablesFragment(viewController), "EditMenuFragment");
        }
        else {
            Log.e(TAG, "Something went wrong....");
        }
    }

    @Override
    public void onTablesEditFinished(boolean isSuccessful, List<Table> menuItems) {
        if (isSuccessful) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDataEditFinish(Restaurant restaurant, Branch branch) {
        progressBar.setVisibility(View.INVISIBLE);

        if (restaurant == null || branch == null) {
            Log.e(TAG, "restaurant or branch are NULL!");
        }
        // TODO: 1/2/2022 Assign restId and branchId to current user`s document

        // TODO: 1/2/2022 Find a way to navigate back to ManagementMainActivity and reload the data.
    }


    private void moveToFragment(Fragment fragment, String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .addToBackStack(fragmentTag)
                .replace(R.id.frameLayoutDataActivity, fragment)
                .commit();
    }
}