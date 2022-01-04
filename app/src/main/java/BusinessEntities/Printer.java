package BusinessEntities;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import API.IOrderController;
import API.IOrderListener;
import API.Models.IOrder;

public class Printer implements IOrderListener {

    private Queue<Object> orders;
    private Context context;

    public Printer(Context context){
        orders = new LinkedList<>();
        this.context = context;
    }

    public void print(){
        while(!orders.isEmpty()){
            Toast.makeText(context, orders.poll().toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onOrderReceived(@NonNull IOrder order) {
        orders.add(order);
        print();
    }
}