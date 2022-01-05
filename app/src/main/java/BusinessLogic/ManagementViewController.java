package BusinessLogic;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.annotation.Nonnull;

import API.Controllers.IManagementViewController;
import API.Database.DatabaseRequestCallback;
import API.Views.IManagementView;
import BusinessEntities.Branch;
import BusinessEntities.Table;
import DataAccessLayer.RestDB;

public class ManagementViewController implements IManagementViewController {

    private final String TAG = "ManagementController";
    private IManagementView managementView;
    private String branchId;
    private String restId;

    private Branch branch;

    public ManagementViewController(IManagementView view, String restId, String branchId) {
        this.managementView = view;
        this.branchId = branchId;
        this.restId = restId;

        RestDB.getInstance()
                .attachOrderListener(restId, branchId,
                new OrderManager(view.getServiceUnits())
                );

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

    @Override
    public void onTableItemClicked(Table table) {
        if (table != null) {
            managementView.loadTableDetailsFragment(table);
        }
        else {
            managementView.onDataFailure("table is null, can't load details");
        }
    }

    @Override
    public void onKitchenButtonClicked() {
        managementView.loadKitchenFragment();
    }
}