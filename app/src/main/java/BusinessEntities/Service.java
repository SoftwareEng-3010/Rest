package BusinessEntities;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import API.Models.IOrder;
import API.Models.IServiceUnit;
import API.Constants.Constants;

public class Service implements IServiceUnit {

    private final String TAG = "Service";

    private List<IOrder> orders;
    private final int type = Constants.USER_TYPE_SERVICE;


    public Service() {
        this.orders = new ArrayList<>();
    }

    @Override
    public List<IOrder> getOrders() {
        return this.orders;
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
        return type;
    }
}
