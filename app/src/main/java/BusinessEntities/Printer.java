package BusinessEntities;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.LinkedList;
import java.util.Queue;

import API.IOrderListener;
import API.Models.IOrder;
import API.Models.IPrinter;

public class Printer implements IPrinter, IOrderListener {

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
