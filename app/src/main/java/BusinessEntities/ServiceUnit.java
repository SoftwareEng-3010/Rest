package BusinessEntities;

import androidx.annotation.NonNull;

import java.util.List;

import API.BusinessEntitiesInterface.IOrder;
import API.BusinessEntitiesInterface.IServiceUnit;

public class ServiceUnit implements IServiceUnit {

    @Override
    public List<IOrder> getOrders() {
        return null;
    }

    @Override
    public void receiveOrder(@NonNull IOrder order) {

    }

    @Override
    public void addToOrder(IOrder order, List<Item> items) {

    }

    @Override
    public void removeFromOrder(IOrder order, List<Item> items) {

    }
}
