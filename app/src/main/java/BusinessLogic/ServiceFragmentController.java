package BusinessLogic;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.exercise_5.R;

import java.util.ArrayList;
import java.util.List;

import API.Controllers.IServiceViewController;
import API.Database.DatabaseRequestCallback;
import API.Views.IServiceView;
import BusinessEntities.Bill;
import BusinessEntities.Branch;
import BusinessEntities.Table;
import DataAccessLayer.RestDB;
import UI.OnSwipeTouchListener;
import UIAdapters.TableGridAdapter;

public class ServiceFragmentController implements IServiceViewController {

    private String TAG = "ServiceFragmentController";

    private IServiceView serviceView;

    private Branch branch;
    private List<Table> tables;
    private RestDB db = RestDB.getInstance();

    public ServiceFragmentController(@NonNull IServiceView serviceView, String restId, String branchId) {
        this.serviceView = serviceView;
//        this.branch = branch;
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
    }

    @Override
    public List<Table> getTables() {
        return this.tables;
    }

    @Override
    public void onSwipeLeft() {

    }

    @Override
    public void onSwipeRight() {

    }
}
