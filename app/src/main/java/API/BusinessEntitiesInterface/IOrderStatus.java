package API.BusinessEntitiesInterface;

import java.util.List;

import BusinessEntities.Item;

public interface IOrderStatus {

    public List<Item> getPendingItems(IOrder order);

    public boolean isComplete();
}
