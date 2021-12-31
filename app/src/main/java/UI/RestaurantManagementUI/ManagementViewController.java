package UI.RestaurantManagementUI;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import API.Database.Database;
import API.Database.DatabaseRequestCallback;
import BusinessEntities.Branch;
import DataAccessLayer.RestDB;
import UI.OnSwipeTouchListener;
import UI.RestaurantManagementUI.ServiceUnitsUI.ManagementHomeFragment;
import UI.RestaurantManagementUI.ServiceUnitsUI.ServiceStaffFragment;

public class ManagementViewController implements IManagementViewController{

    private final String TAG = "ManagementViewController";
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

        RestDB.getInstance()
                .getBranch(restId, branchUid, new DatabaseRequestCallback() {
            @Override
            public void onObjectReturnedFromDB(@Nullable Object obj) {
                if (obj == null) {
                    view.onDataFailure("Branch object came back null from database (Branch_UID: " + branchUid + ")");
                }

                else {
                    branch = (Branch) obj;
                    homeFragment = new ManagementHomeFragment();
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
        if (serviceFragment == null) {
            this.serviceFragment = new ServiceStaffFragment();
        }
        managementView.loadFragment(serviceFragment);
    }

    @Override
    public void onKitchenButtonClicked() {
        Log.e("ManagementController", "awd");
//        if (kitchenFragment == null) {
//            this.kitchenFragment = new KitchenFragment();
//        }
//        managementView.loadFragment(kitchenFragment);
    }
}
