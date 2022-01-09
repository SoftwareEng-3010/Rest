package BusinessEntities;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import API.Constants.Constants;
import API.Controllers.IOrderManager;
import API.Models.IOrder;
import API.Models.IServiceUnit;

public class Kitchen implements IServiceUnit {

    private String TAG = "Kitchen";

    private IOrderManager orderController;

    @Override
    public void onOrderReceived(@NonNull IOrder order) {
        return;
    }

    @Override
    public int getServiceType() {
        return Constants.USER_TYPE_KITCHEN;
    }

    @Override
    public List<IOrder> getOrders() {
        return null;
    }

//    @Override
    public IOrderManager getController() {
        return this.orderController;
    }

    @Override
    public void update(@NonNull String message) {
        Log.e(TAG, "Controller: " + message);
    }
}
