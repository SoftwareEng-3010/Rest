package BusinessLogic;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import API.Controllers.IServiceViewController;
import BusinessEntities.Bill;
import BusinessEntities.Branch;
import BusinessEntities.Table;
import UIAdapters.TableGridAdapter;

public class ServiceViewController implements IServiceViewController {

    private String TAG = "ServiceViewController";
    private List<Table> tables;

    public ServiceViewController(@NonNull Branch branch) {
        tables = new ArrayList<>(branch.getTables());
    }

    @Override
    public void onBillCloseBtnClicked(@Nullable Bill bill) {
        Log.e(TAG, "onBillCloseBtnClicked()");
    }

    @Override
    public void onTableItemClicked(Table table/*, int tableNumber*/) {
        // Maybe an integer (tableNumber) will suffice.
        Log.e(TAG, "onTableItemClicked()");
    }
}
