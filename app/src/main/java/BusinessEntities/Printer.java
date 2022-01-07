package BusinessEntities;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import API.Models.IOrder;
import API.Models.IPrinter;

public class Printer implements IPrinter {

    private List<Object> orders;
    private Context context;
    private int location;

    public Printer(){
        orders = new LinkedList<>();
        this.context = context;
    }

    public Printer(int location){
        setLocation(location);
    }

    public List<Object> print(){
        List<Object> temp = new ArrayList<>(orders);
        orders.clear();
        return temp;
    }

    @Override
    public void setLocation(int location) {
        this.location = location;
    }

    @Override
    public int getLocation(){
        return this.location;
    }

    @Override
    public void onOrderReceived(@NonNull IOrder order) {
        orders.add(order);
        print();
    }
}
