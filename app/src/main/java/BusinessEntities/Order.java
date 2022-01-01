package BusinessEntities;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.PropertyName;

import java.util.ArrayList;
import java.util.List;

import API.Models.IOrder;

public class Order implements IOrder {

    @DocumentId
    private String orderId;
    @PropertyName("order_items")
    private List<Item> orderItems;
    private Table table;

    public Order() {}

    public Order(List<Item> items, Table table) {
        this.orderItems = new ArrayList<>(items);
        this.table = table;
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

    @PropertyName("order_items")
    @Override
    public List<Item> getOrderItems() {
        return orderItems;
    }

    @Override
    public Table getTable() {
        return table;
    }
}
