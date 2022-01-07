package BusinessLogic;

import android.util.Log;

import androidx.annotation.Nullable;

import API.Constants.Constants;
import API.Controllers.IHomeViewController;
import API.Controllers.IManagementViewController;
import API.Controllers.IServiceViewController;
import API.Database.DatabaseRequestCallback;
import API.Views.IManagementView;
import API.SwipeGestureListener;
import BusinessEntities.Branch;
import DataAccessLayer.RestDB;

public class ManagementViewController implements IManagementViewController, SwipeGestureListener {

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    private final String TAG = "ManagementController";
    private IManagementView managementView;
    private String restId, branchId;
    private int currentFragment = Constants.MANAGEMENT_HOME_SCREEN;

    private IHomeViewController homeViewController;
    private IServiceViewController serviceViewController;
//    private IKitchenViewController kitchenViewController;

    private Branch branch;

    public ManagementViewController(IManagementView view, String restId, String branchId) {
        this.managementView = view;
        this.branchId = branchId;
        this.restId = restId;



        RestDB.getInstance()
                .getBranch(
                        restId, branchId,
                        new DatabaseRequestCallback() {

            @Override
            public void onObjectReturnedFromDB(@Nullable Object obj) {
                if (obj == null) {
                    Log.e(TAG, "Branch came back null from database");
                    view.onDataFailure("Branch object came back null from database (Branch_UID: " + branchId + ")");
                }

                else {
                    branch = (Branch) obj;
                    view.loadHomeFragment();
                    RestDB.getInstance()
                            .attachOrderListener(restId, branchId,
                                    new OrderManager(view.getServiceUnits(), view.getOrderListeners())
                            );
                }
            }
        });

    }

    @Override
    public void onHomeButtonClicked() {
        managementView.loadHomeFragment();
    }

    @Override
    public void onServiceButtonClicked() {
        managementView.loadServiceFragment();
    }

//    @Override
//    public void onTableItemClicked(Table table) {
//        if (table != null) {
//            managementView.loadTableDetailsFragment(table);
//        }
//        else {
//            managementView.onDataFailure("table is null, can't load details");
//        }
//    }

    @Override
    public void onKitchenButtonClicked() {
        managementView.loadKitchenFragment();
    }

    @Override
    public void onSwipeLeft() {
//        managementView.
    }

    @Override
    public void onSwipeRight() {

    }
}