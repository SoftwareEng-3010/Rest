package API.BusinessEntitiesInterface;

import androidx.annotation.NonNull;

import java.util.List;

import BusinessEntities.Item;

public interface IServiceUnit {

    public List<IOrder> getOrders();

    public void receiveOrder(@NonNull IOrder order);

    public void removeFromOrder(IOrder order, List<Item> items);

    public void addToOrder(IOrder order, List<Item> items);
}
