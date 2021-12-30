package BusinessEntities;

import androidx.annotation.NonNull;

import java.util.List;

import API.IOrderHandler;
import API.Models.IOrder;
import API.Models.IServiceUnit;

public class Kitchen implements IServiceUnit {

    private Printer<Order> printer;

    @Override
    public void onOrderReceived(@NonNull IOrder order) {

    }

    @Override
    public List<IOrder> getOrders() {
        return null;
    }

    @Override
    public void update(String message) {

    }
}
