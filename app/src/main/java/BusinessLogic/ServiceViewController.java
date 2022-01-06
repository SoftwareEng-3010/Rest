package BusinessLogic;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import API.Controllers.IManagementViewController;
import API.Controllers.IServiceViewController;
import API.Database.DatabaseRequestCallback;
import API.Views.IManagementView;
import API.Views.IServiceView;
import BusinessEntities.Bill;
import BusinessEntities.Branch;
import BusinessEntities.Service;
import BusinessEntities.Table;
import DataAccessLayer.RestDB;

public class ServiceViewController implements IServiceViewController {

    private String TAG = "ServiceViewController";

    // View
    private IManagementView managementView;
    private IServiceView serviceView;
    // Model
    private Branch branch;
//    private Service service;
    private List<Table> tables;
    private RestDB db = RestDB.getInstance();

    public ServiceViewController(@NonNull IManagementView managementView, @NonNull IServiceView serviceView, String restId, String branchId) {
        this.serviceView = serviceView;
        this.managementView = managementView;
//        service = new Service();

        db.getBranch(restId, branchId,
                new DatabaseRequestCallback() {
                    @Override
                    public void onObjectReturnedFromDB(@Nullable Object obj) {
                        if (obj == null) {
                            Log.e(TAG, "obj is null");
                        }
                        else {
                            branch = (Branch)obj;
                            if (null != branch.getTables()) {
                                tables = branch.getTables();
                                serviceView.setupTableGridView(branch.getTables());
                            }
                            else {
                                Log.e(TAG, "tables are null");
                            }
                        }
                    }
                });
    }

    @Override
    public void onBillCloseBtnClicked(@Nullable Bill bill) {
        Log.e(TAG, "onBillCloseBtnClicked()");
    }

    @Override
    public void onTableItemClicked(Table table/*, int tableNumber*/) {
        // Maybe an integer (tableNumber) will suffice.
        Log.e(TAG, "onTableItemClicked()" + table.getTableNumber());

        serviceView.setupTableGridView(tables);
    }

    @Override
    public List<Table> getTables() {
        return this.tables;
    }

    @Override
    public void onSwipeLeft() {
        managementView.loadHomeFragment();
    }

    @Override
    public void onSwipeRight() {
        managementView.loadKitchenFragment();
    }
}
