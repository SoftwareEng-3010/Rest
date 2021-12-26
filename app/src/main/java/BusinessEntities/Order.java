package BusinessEntities;

import com.google.firebase.firestore.DocumentId;

import java.util.List;

import API.BusinessEntitiesInterface.IOrder;

public class Order implements IOrder {

    @DocumentId
    private String docId;
    private List<Item> order;
    private Table table;

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
}
