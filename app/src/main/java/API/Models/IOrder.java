package API.Models;

import androidx.annotation.NonNull;

import java.util.List;

import BusinessEntities.Item;
import BusinessEntities.Table;

public interface IOrder {

    public String getDocId();

    public List<Item> getOrderItems();

    public Table getTable();

    public void addItem(@NonNull Item item);

    public void removeItem(@NonNull Item item);
}
