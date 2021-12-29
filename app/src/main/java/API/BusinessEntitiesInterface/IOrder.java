package API.BusinessEntitiesInterface;

import java.util.List;

import BusinessEntities.Item;
import BusinessEntities.Table;

public interface IOrder {

    public String getOrderID();

    public List<Item> getOrderItems();

    public Table getTable();

    public List<Item> addToOrder(Item item);
}
