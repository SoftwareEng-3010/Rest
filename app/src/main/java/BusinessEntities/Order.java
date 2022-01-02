package BusinessEntities;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.PropertyName;

import java.util.ArrayList;
import java.util.List;

import API.Models.IOrder;

public class Order implements IOrder {

    @DocumentId
    private String docId;

    @PropertyName("order_items")
    private List<Item> orderItems;
    private Table table;

    public Order() {}

    public Order(List<Item> items, Table table) {
        this.orderItems = new ArrayList<>(items);
        this.table = table;
    }

    public Order(IOrder other) {
        this.docId = other.getDocId();
        this.orderItems = new ArrayList<>(other.getOrderItems());
        this.table = other.getTable();
    }

    public String getDocId() {
        return docId;
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

    @Override
    public void addItem(@NonNull Item item) {
        if (this.orderItems == null)
            this.orderItems = new ArrayList<>();
        this.orderItems.add(item);
    }

    @Override
    public void removeItem(@NonNull Item item) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();

        } else {
            int indexToRemove = orderItems.indexOf(item);
            if (indexToRemove >= 0) {
                this.orderItems.remove(indexToRemove);
            }
        }
    }

    @Override
    public String toString() {
        String order = "Order id: " + getDocId() + "\n";

        for (Item i : getOrderItems())
            order += (i.getName() + "\t" + i.getPrice() + "\n");

        return order;
    }
}
