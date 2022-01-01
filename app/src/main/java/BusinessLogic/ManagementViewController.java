package BusinessLogic;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import API.Controllers.IManagementViewController;
import API.Database.DatabaseRequestCallback;
import API.Views.IManagementView;
import BusinessEntities.Branch;
import DataAccessLayer.RestDB;
import UI.RestaurantManagementUI.ServiceUnitsUI.HomeFragment;
import UI.RestaurantManagementUI.ServiceUnitsUI.KitchenFragment;
import UI.RestaurantManagementUI.ServiceUnitsUI.ServiceFragment;

public class ManagementViewController implements IManagementViewController {

    private final String TAG = "ManagementController";
    private IManagementView managementView;
    private String branchId;
    private String restId;

    private Branch branch;

    private Fragment homeFragment;
    private Fragment serviceFragment;
    private Fragment kitchenFragment;

    public ManagementViewController(IManagementView view, String restId, String branchUid) {
        this.managementView = view;
        this.branchId = branchUid;
        this.restId = restId;

        homeFragment = new HomeFragment();
        serviceFragment = new ServiceFragment();
        kitchenFragment = new KitchenFragment();


        RestDB.getInstance()
                .getBranch(restId, branchUid, new DatabaseRequestCallback() {
            @Override
            public void onObjectReturnedFromDB(@Nullable Object obj) {
                if (obj == null) {
                    Log.e(TAG, "Branch came back null from database");
                    view.onDataFailure("Branch object came back null from database (Branch_UID: " + branchUid + ")");
                }

                else {
                    branch = (Branch) obj;
                    view.loadFragment(homeFragment);
                }
            }
        });
    }



    @Override
    public void onHomeButtonClicked() {
        managementView.loadFragment(homeFragment);
    }

    @Override
    public void onServiceButtonClicked() {
        managementView.loadFragment(serviceFragment);
    }

    @Override
    public void onKitchenButtonClicked() {
        managementView.loadFragment(kitchenFragment);
    }
}
