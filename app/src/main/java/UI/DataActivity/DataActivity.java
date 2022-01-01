package UI.DataActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.exercise_5.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import BusinessEntities.Address;
import BusinessEntities.Branch;
import BusinessEntities.Item;
import BusinessEntities.Menu;
import BusinessEntities.Restaurant;
import UI.DataActivity.Controller.DataEditViewController;
import UI.DataActivity.Controller.DataViewController;
import UI.DataActivity.View.CreateBranchFragment;
import UI.DataActivity.View.CreateMenuFragment;
import UI.DataActivity.View.CreateRestaurantFragment;
import UI.DataActivity.View.DataEditView;

public class DataActivity extends AppCompatActivity implements DataEditView {

    // Business entities to be constructed
//    private Restaurant restaurant;
//    private Branch branch;
//    private Menu menu;

    private final String TAG = "DataActivity";
    // A placeholder for activity fragments
    private FrameLayout frameLayout;


    private DataViewController viewController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        viewController = new DataEditViewController(this);

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .addToBackStack("CreateRestaurantFragment")
                .replace(R.id.frameLayoutDataActivity, new CreateRestaurantFragment(viewController))
                .commit();
    }

    @Override
    public void onRestaurantEditFinished(boolean isSuccessful, String restaurantName) {

        if (isSuccessful) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .addToBackStack("EditRestaurantFragment")
                    .replace(R.id.frameLayoutDataActivity, new CreateBranchFragment(viewController))
                    .commit();
        }
    }

    @Override
    public void onBranchEditFinished(boolean isSuccessful, Address address, boolean isKosher) {
        if (isSuccessful) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .addToBackStack("EditBranchFragment")
                    .replace(R.id.frameLayoutDataActivity, new CreateMenuFragment(viewController))
                    .commit();
        }
    }

    @Override
    public void onMenuEditFinished(boolean isSuccessful, List<Item> menuItems) {
        if (isSuccessful) {
            Log.e(TAG, "Finished creating a restaurant");
        }
        else {
            Log.e(TAG, "Something went wrong....");
        }
    }
}