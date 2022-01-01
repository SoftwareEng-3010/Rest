package BusinessLogic;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import API.Constants.Constants;
import API.IOrderController;
import API.IOrderListener;
import API.Models.IBranchManagerUser;
import API.Models.IOrder;
import API.Models.IServiceUnit;
import BusinessEntities.Item;
import BusinessEntities.Order;
import DataAccessLayer.RestDB;

public class OrderManager implements IOrderController {

    private List<IServiceUnit> units;

    private final String TAG = "OrderManager";

    public OrderManager(@NonNull List<IServiceUnit> units) {
        this.units = new ArrayList<>(units);
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
        Log.e(TAG, "onEvent()");

        if (value != null) {
            for (DocumentSnapshot doc : value.getDocuments()) {
                IOrder order = doc.toObject(Order.class);
                if (order == null) {Log.e(TAG, "Order is null"); return;}

                // Copy the order (The only way to copy the document id of the order)
                IOrder kitchenOrder = new Order(order);
                IOrder serviceOrder = new Order(order);

                // Discard any unnecessary item from each order
                for (Item item : order.getOrderItems()) {
                    if ( ! item.getServiceUnit().equals("kitchen")) {
                        kitchenOrder.removeItem(item);
                    }
                    else if ( ! item.getServiceUnit().equals("service")){
                        serviceOrder.removeItem(item);
                    }
                }

                // Check the types of service units and let them handle the order:
                for (IServiceUnit unit : units) {
                    // If Kitchen order
                    if (unit.getServiceType() == Constants.USER_TYPE_KITCHEN)
                        unit.onOrderReceived(kitchenOrder);

                    // If Service order
                    if (unit.getServiceType() == Constants.USER_TYPE_SERVICE)
                        unit.onOrderReceived(serviceOrder);
                }
            }
        }
    }

    @Override
    public List<IServiceUnit> getSubscribedServiceUnits() {
        return units;
    }

    @Override
    public boolean registerUnit(@NonNull IServiceUnit unit) {
        return this.units.add(unit);
    }

    @Override
    public boolean updateServiceUnit(@NonNull IServiceUnit unit) {
        return false;
    }

    @Override
    public void onOrderComplete(@NonNull String orderId) {
        Log.e(TAG, "Order Complete: " + orderId);
    }

    @Override
    public void updateSubscribers(@NonNull String message) {
        Log.e(TAG, "Updating subscribers:\n");
    }
}
