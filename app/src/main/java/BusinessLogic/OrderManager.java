package BusinessLogic;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import API.Constants.Constants;
import API.IOrderController;
import API.IOrderListener;
import API.Models.IOrder;
import API.Models.IPrinter;
import API.Models.IServiceUnit;
import BusinessEntities.Bill;
import BusinessEntities.Item;
import BusinessEntities.Order;
import BusinessEntities.Printer;
import DataAccessLayer.RestDB;
import UI.RestaurantManagementUI.ServiceUnitsUI.KitchenFragment;

public class OrderManager implements IOrderController {

    private List<IServiceUnit> units;
    private List<IPrinter> printers;

    private List<IOrderListener> orderListeners;
    private final String TAG = "OrderManager";

    public OrderManager(@NonNull List<IServiceUnit> units, List<IOrderListener> orderListeners) {
        this.orderListeners = new ArrayList<>(orderListeners);
        this.units = new ArrayList<>(units);
        this.printers = new ArrayList<>();
        for (IServiceUnit unit : units){
            printers.add(new Printer(unit.getServiceType()));
        }
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
        Log.e(TAG, "onEvent()");

        if (value != null) {
            for (DocumentChange docChange : value.getDocumentChanges()) {
                // TODO: 1/6/2022 Maybe use a boolean flag to indicate first time connecting to db
                if (docChange.getType() == DocumentChange.Type.ADDED) {
//                    DocumentSnapshot doc =
                    Order order = docChange.getDocument().toObject(Order.class);
                    if (order == null) {
                        Log.e(TAG, "Order is null");
                        return;
                    }

                    // Add order to table's bill
                    if(order.getTable().getBill() == null) {
                        Log.w(TAG, "Bill is null");
                        Bill bill = new Bill();
                        bill.addOrder(order);
                        order.getTable().setBill(bill);
                    }

                    // Copy the order (The only way to copy the document id of the order)
                    IOrder kitchenOrder = new Order(order);
                    IOrder serviceOrder = new Order(order);
//                    order.getTable();

                    // Discard any unnecessary item from each order
                    for (Item item : order.getOrderItems()) {
                        if (!item.getServiceUnit().equals("kitchen")) {
                            kitchenOrder.removeItem(item);
                        } else if (!item.getServiceUnit().equals("service")) {
                            serviceOrder.removeItem(item);
                        }
                    }

                    for (IPrinter printer : printers){
                        if(printer.getLocation() == Constants.USER_TYPE_KITCHEN){
                            printer.onOrderReceived(kitchenOrder);
                        }
                    }
                    // Check the types of service units and let them handle the order:
                    // TODO: Some other time consider upgrading this code
                    for (IServiceUnit unit : units) {
                        // If Kitchen order
                        if (unit.getServiceType() == Constants.USER_TYPE_KITCHEN){
                            if (!kitchenOrder.getOrderItems().isEmpty()) {
                                unit.onOrderReceived(kitchenOrder);
                            }
                        }
                        // If Service order
                        if (unit.getServiceType() == Constants.USER_TYPE_SERVICE){
                            if (!serviceOrder.getOrderItems().isEmpty()){
                                unit.onOrderReceived(serviceOrder);
                            }
                        }
//                        if (unit.getServiceType() == Constants.USER_TYPE_KITCHEN_PRINTER) {
//                            if (!serviceOrder.getOrderItems().isEmpty()) {
//                                unit.onOrderReceived(kitchenOrder);
//                            }
//                        }
//
//                        if (unit.getServiceType() == Constants.USER_TYPE_SERVICE_PRINTER) {
//                            if (!serviceOrder.getOrderItems().isEmpty()) {
//                                unit.onOrderReceived(serviceOrder);
//                            }
//                        }
                    }

                    for(IOrderListener orderListener : orderListeners){
                        if(orderListener instanceof KitchenFragment){
                            orderListener.onOrderReceived(kitchenOrder);
                        }
                        else{
                            orderListener.onOrderReceived(serviceOrder);
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<IServiceUnit> getSubscribedServiceUnits() {
        return units;
    }

    @Override
    public void detachSnapshotListener() {
//        RestDB.getInstance().
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
