package API.Models;

import java.util.List;

import BusinessEntities.Item;
import BusinessEntities.Table;

public interface IOrder {

    public String getOrderId();

    public List<Item> getOrderItems();

    public Table getTable();
}
