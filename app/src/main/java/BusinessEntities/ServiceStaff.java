package BusinessEntities;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.List;

import API.IOrderController;
import API.Models.IOrder;
import API.Models.IServiceUnit;
import API.Constants.Constants;

public class ServiceStaff implements IServiceUnit {

    private final String TAG = "ServiceStaff";

    private IOrderController orderController;
    private List<IOrder> orders;
    private String uid;
    private String email = null;
    private final int type = Constants.USER_TYPE_SERVICE;


    public ServiceStaff() {
//        type = Constants.TYPE_SERVICE_STAFF_USER;
    }

    @Override
    public List<IOrder> getOrders() {
        return this.orders;
    }

    @Override
    public IOrderController getController() {
        return this.orderController;
    }

    @Override
    public void update(@NonNull String message) {
        Log.e(TAG, "Controller->Service: " + message);
    }

    @Override
    public void onOrderReceived(@NonNull IOrder order) {
        update(order.toString());
    }

    @Override
    public int getServiceType() {
        return Constants.USER_TYPE_SERVICE;
    }
}
