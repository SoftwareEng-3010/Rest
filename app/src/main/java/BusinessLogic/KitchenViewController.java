package BusinessLogic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import API.Controllers.IKitchenFragmentController;
import API.Database.DatabaseRequestCallback;
import API.Views.IKitchenView;
import API.Views.IManagementView;
import BusinessEntities.Branch;
import BusinessEntities.Kitchen;
import DataAccessLayer.RestDB;

public class KitchenViewController implements IKitchenFragmentController {
    private RestDB db = RestDB.getInstance();

    // View
    private IManagementView managementView;
    private IKitchenView kitchenView;
    // Model
    private Branch branch;
    private Kitchen kitchen;

    public KitchenViewController(@NonNull IManagementView managementView, @NonNull IKitchenView kitchenView, String restId, String branchId) {
        this.managementView = managementView;
        this.kitchenView = kitchenView;

        if (restId != null && branchId != null) {

            db.getBranch(
                    restId, branchId, new DatabaseRequestCallback() {
                @Override
                public void onObjectReturnedFromDB(@Nullable Object obj) {
                    if (obj != null) {
                        branch = (Branch) obj;
                        kitchenView.setupUI();
                    }
                }
            });
        }
    }

    @Override
    public void onSwipeLeft() {
        managementView.loadServiceFragment();
    }

    @Override
    public void onSwipeRight() {}
}
