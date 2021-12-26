package BusinessEntities;

import java.util.List;

import API.BusinessEntitiesInterface.IOrder;
import API.BusinessEntitiesInterface.IOrderStatus;

public class OrderStatus implements IOrderStatus {

    private List<Item> items;

    @Override
    public List<Item> getPendingItems(IOrder order) {

        if (!isComplete()) {
            return order.getOrderItems();
        }
        return null;
    }

    @Override
    public boolean isComplete() {
        return items.isEmpty();
    }
}
