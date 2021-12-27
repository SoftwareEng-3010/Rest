package BusinessEntities;

import java.util.List;

import API.BusinessEntitiesInterface.IOrder;
import API.BusinessEntitiesInterface.IOrderStatus;

public class OrderStatus implements IOrderStatus {

    @Override
    public List<Item> getPendingItems(IOrder order) {
        return null;
    }

    @Override
    public boolean isComplete() {
        return false;
    }
}
