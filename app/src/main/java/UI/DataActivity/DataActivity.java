package UI.DataActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.exercise_5.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import BusinessEntities.Address;
import BusinessEntities.Branch;
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

    // A placeholder for activity fragments
    private FrameLayout frameLayout;


    private DataViewController viewController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);


        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new CreateRestaurantFragment());
        fragments.add(new CreateBranchFragment());
        fragments.add(new CreateMenuFragment());

        viewController = new DataEditViewController(this, null);

//        moveToNextFragment(new CreateRestaurantFragment());

    }

    @Override
    public void onDataEditFinished(boolean isSuccessful, String message) {

    }

    public void moveToNextFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayoutDataActivity, fragment);
        fragmentTransaction.commit();
    }


}