package BusinessEntities;

import androidx.annotation.NonNull;

import java.util.List;

import API.BusinessEntitiesInterface.IOrder;
import API.BusinessEntitiesInterface.IServiceUnit;

public class ServiceUnit implements IServiceUnit {

    private List<IOrder> orders;

    @Override
    public List<IOrder> getOrders() {
        return orders;
    }

    @Override
    public void receiveOrder(@NonNull IOrder order) {

        if (orders.contains(order)) {
            addToOrder(order, order.getOrderItems());
        }
        else {
            orders.add(order);
        }
    }

    @Override
    public void addToOrder(IOrder order, List<Item> items) {

        for (Item i : items) {
            order.getOrderItems().add(i);
        }
    }

    @Override
    public void removeFromOrder(IOrder order, List<Item> items) {

        for (Item i : items) {
            order.getOrderItems().remove(i);
        }
    }
}
