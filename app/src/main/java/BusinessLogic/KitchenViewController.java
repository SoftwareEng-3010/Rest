package BusinessLogic;

import androidx.annotation.NonNull;

import java.util.List;

import API.Controllers.IKitchenFragmentController;
import API.Views.IKitchenView;
import API.Views.IManagementView;
import API.Views.IServiceView;
import BusinessEntities.Branch;
import BusinessEntities.Kitchen;
import BusinessEntities.Service;
import BusinessEntities.Table;
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
    }

    @Override
    public void onSwipeLeft() {
        managementView.loadServiceFragment();
    }

    @Override
    public void onSwipeRight() {}
}
