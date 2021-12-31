package UI.RestaurantManagementUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.exercise_5.R;

import API.Constants.Constants;
import API.Views.SwipeGestureListener;
import UI.CustomersUI.QRCodeActivity;
import UI.DataActivity.DataActivity;
import UI.OnSwipeTouchListener;
import UI.RestaurantManagementUI.ServiceUnitsUI.ManagementHomeFragment;
import UI.RestaurantManagementUI.ServiceUnitsUI.ServiceStaffFragment;

public class ManagementMainActivity extends AppCompatActivity implements IManagementView, SwipeGestureListener {

    private FrameLayout frameLayout;

    private IManagementViewController viewController;
    private Fragment serviceStaffFragment;
    private Fragment managementHomeFragment;
    private Button btnHome;
    private Button btnService;
    private Button btnKitchen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_main);

        // Check if the manager have signed in as a customer:
        boolean manager_is_logged_in_as_customer = getIntent()
                .getBooleanExtra("is_manager_logged_in_as_customer", false);

        if (manager_is_logged_in_as_customer) {
            moveToQRCodeActivity();
        }

        init();
    }


    private void moveToDataActivity() {
//        if (branchUid == null) {
            Intent dataActivity = new Intent(this, DataActivity.class);
            startActivity(dataActivity);
//        }
    }

    @Override
    public void onSwipeLeft() {Toast.makeText(this, "Swipe right", Toast.LENGTH_SHORT).show();}

    @Override
    public void onSwipeRight() {
        Toast.makeText(this, "Swipe right", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwipeTop() {
        Toast.makeText(this, "Swipe up", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwipeBottom() {
        Toast.makeText(this, "Swipe down", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.frame_layout_management, fragment)
                .commit();
    }

    @Override
    public void onDataFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    private void init() {

        managementHomeFragment = new ManagementHomeFragment();
        serviceStaffFragment = new ServiceStaffFragment();


        String managerUid = getIntent().getStringExtra("manager_uid");
        String branchId = getIntent().getStringExtra("branch_id");
        String restId = getIntent().getStringExtra("rest_id");


        frameLayout = (FrameLayout) findViewById(R.id.frame_layout_management);

        viewController = new ManagementViewController(this, restId, branchId);

        btnHome = (Button) findViewById(R.id.btn_management_home);
        btnService = (Button) findViewById(R.id.btn_management_service);
        btnKitchen = (Button) findViewById(R.id.btn_management_kitchen);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewController.onHomeButtonClicked();
            }
        });
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewController.onServiceButtonClicked();
            }
        });
        btnKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewController.onKitchenButtonClicked();
            }
        });
    }

    private void moveToQRCodeActivity() {
        Intent qrActivity = new Intent(this, QRCodeActivity.class);
        qrActivity.putExtra("user_type", Constants.USER_TYPE_BRANCH_MANAGER);
        startActivity(qrActivity);
    }

}