package BusinessEntities;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import API.Constants.Constants;
import API.IOrderController;
import API.IOrderListener;
import API.Models.IOrder;
import API.Models.IPrinter;
import API.Models.IServiceUnit;

public class Printer implements IPrinter {

    private Queue<Object> orders;
    private Context context;
    private int location;

    public Printer(){
        orders = new LinkedList<>();
        this.context = context;
    }

    public Printer(int location){
        setLocation(location);
    }

    public void print(){
        while(!orders.isEmpty()){
            Toast.makeText(context, orders.poll().toString(), Toast.LENGTH_LONG).show();
        }
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
