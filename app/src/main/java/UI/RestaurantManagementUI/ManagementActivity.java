package UI.RestaurantManagementUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.exercise_5.R;

import java.util.ArrayList;
import java.util.List;

import API.Constants.Constants;
import API.Controllers.IManagementViewController;
import API.Controllers.IServiceViewController;
import API.Models.IServiceUnit;
import API.Views.IManagementView;
import BusinessEntities.Printer;

import BusinessEntities.Table;
import BusinessLogic.ManagementViewController;
import BusinessLogic.OrderManager;
import UI.CustomersUI.QRCodeActivity;
import UI.DataActivity.DataActivity;
import UI.RestaurantManagementUI.ServiceUnitsUI.HomeFragment;
import UI.RestaurantManagementUI.ServiceUnitsUI.KitchenFragment;
import UI.RestaurantManagementUI.ServiceUnitsUI.ServiceFragment;
import UI.RestaurantManagementUI.ServiceUnitsUI.TableDetailsFragment;

public class ManagementActivity extends AppCompatActivity implements IManagementView {

    private final String TAG = "ManagementMain";

    private FrameLayout frameLayout;

    private IManagementViewController mainController;
    private IServiceViewController serviceViewController;

    private Button btnHome;
    private Button btnService;
    private Button btnKitchen;

    private HomeFragment homeFragment;
    private KitchenFragment kitchenFragment;
    private ServiceFragment serviceFragment;
//    private TableDetailsFragment tableDetailsFragment;

    private String managerUid, branchId, restId;

    // TODO: 1/4/2022 Add Textviews for brand details
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

        if (restId == null || branchId == null) {

            if (restId == null) {Log.e(TAG, "restId is NULL!");}

            if (branchId == null) {
                new AlertDialog.Builder(this)
                        .setCancelable(false)
                        .setMessage("נראה שאין סניף שמנוהל על ידך במאגר הנתונים.\nהנך מועבר לעריכת פרטי הסניף שלך")
                        .setNeutralButton("הבנתי", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveToDataActivity();
                            }
                        })
                        .create()
                        .show();
            }
        }
        // TODO: 1/6/2022 This else statement was removed
        //  because I've called init() from the controller.
//        else {
//            init();
//        }
        mainController = new ManagementViewController(this, restId, branchId);
    }


    private void loadFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.frame_layout_management, fragment)
                .commit();
    }

    @Override
    public void loadKitchenFragment() {
        loadFragment(kitchenFragment);
    }

    @Override
    public void loadServiceFragment() {
        loadFragment(serviceFragment);
    }

    @Override
    public void loadHomeFragment() {
        loadFragment(homeFragment);
    }

    @Override
    public void init() {

        frameLayout = (FrameLayout) findViewById(R.id.frame_layout_management);

        // init view controllers:

        // ManagementActivity's UI Fragments
        serviceFragment = new ServiceFragment();
        Bundle b = new Bundle();
        b.putString(Constants.KEY_BRANCH_ID, branchId);
        b.putString(Constants.KEY_RESTAURANT_ID, restId);
        serviceFragment.setArguments(b);

        homeFragment = new HomeFragment();
        kitchenFragment = new KitchenFragment();

        loadFragment(homeFragment);

        new OrderManager(getServiceUnits());

        btnHome = (Button) findViewById(R.id.btn_management_home);
        btnService = (Button) findViewById(R.id.btn_management_service);
        btnKitchen = (Button) findViewById(R.id.btn_management_kitchen);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnService.setBackgroundColor(Color.TRANSPARENT);
                btnKitchen.setBackgroundColor(Color.TRANSPARENT);
                mainController.onHomeButtonClicked();
            }
        });
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnHome.setBackgroundColor(Color.TRANSPARENT);
                btnKitchen.setBackgroundColor(Color.TRANSPARENT);
                mainController.onServiceButtonClicked();
            }
        });
        btnKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnService.setBackgroundColor(Color.TRANSPARENT);
                btnHome.setBackgroundColor(Color.TRANSPARENT);
                mainController.onKitchenButtonClicked();
            }
        });
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
//        units.add(new Service());
//        units.add(new Kitchen());

//        IServiceUnit kitchenPrinter = new Printer(this);
//        units.add(kitchenPrinter);

        return units;
    }

    private void moveToQRCodeActivity() {
        Intent qrActivity = new Intent(this, QRCodeActivity.class);
        qrActivity.putExtra("user_type", Constants.USER_TYPE_BRANCH_MANAGER);
        startActivity(qrActivity);
    }

    private void moveToDataActivity() {
        Intent dataActivity = new Intent(this, DataActivity.class);
        startActivity(dataActivity);
        finish();
    }
}