package BusinessEntities;

import com.google.firebase.firestore.DocumentId;

import java.util.ArrayList;
import java.util.List;

import API.BusinessEntitiesInterface.IOrder;

public class Order implements IOrder {

    @DocumentId
    private String docId;
    private List<Item> order;
    private Table table;

    public Order() {
        order = new ArrayList<>();
    }

    @Override
    public String getOrderID() {
        return docId;
    }

    @Override
    public List<Item> getOrderItems() {
        return order;
    }

    @Override
    public Table getTable() {
        return table;
    }

    @Override
    public List<Item> addToOrder(Item item) {
        return null;
    }
}
