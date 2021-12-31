package BusinessLogic;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;

import API.IOrderListener;
import API.Models.IBranchManagerUser;
import API.Models.IOrder;
import API.Models.IServiceUnit;

public class OrderManager implements IOrderListener {

    private List<IServiceUnit> units;

    private final String TAG = "OrderManager";

    public OrderManager() {
        this.units = new ArrayList<>();
    }

    @Override
    public void onOrderReceived(@NonNull IOrder order) {

    }
}
