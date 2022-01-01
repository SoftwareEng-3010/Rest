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

import java.util.ArrayList;
import java.util.List;

import API.Constants.Constants;
import API.Controllers.IManagementViewController;
import API.Models.IServiceUnit;
import API.Views.IManagementView;
import BusinessEntities.Kitchen;
import BusinessEntities.ServiceStaff;
import BusinessLogic.ManagementViewController;
import UI.CustomersUI.QRCodeActivity;
import UI.DataActivity.DataActivity;
import UI.RestaurantManagementUI.ServiceUnitsUI.HomeFragment;
import UI.RestaurantManagementUI.ServiceUnitsUI.ServiceFragment;

public class ManagementMainActivity extends AppCompatActivity implements IManagementView {

    private FrameLayout frameLayout;

    private IManagementViewController viewController;
    private Button btnHome;
    private Button btnService;
    private Button btnKitchen;

    private String managerUid, branchId, restId;

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


        managerUid = getIntent().getStringExtra("manager_uid");
        branchId = getIntent().getStringExtra("branch_id");
        restId = getIntent().getStringExtra("rest_id");

        if (restId == null) {
            moveToDataActivity();
        }

        else {
            init();
        }
    }


    private void moveToDataActivity() {
            Intent dataActivity = new Intent(this, DataActivity.class);
            startActivity(dataActivity);
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

    /**
     * This method returns some HARD-CODED service-unit types.
     * This should be avoided by setting up different service-unit types
     * under each branch in database and the make a request to get them.
     * @return - A hard-coded List<> of IServiceUnit`s.
     */
    @Override
    public List<IServiceUnit> getServiceUnits() {
        List<IServiceUnit> units = new ArrayList<>();
        units.add(new ServiceStaff());
        units.add(new Kitchen());
        return units;
    }


    private void init() {

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