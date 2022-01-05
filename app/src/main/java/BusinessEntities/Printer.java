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

public class Printer implements IPrinter, IServiceUnit {

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
    public List<IOrder> getOrders() {
        return null;
    }

    @Override
    public IOrderController getController() {
        return null;
    }

    @Override
    public void update(@NonNull String message) {

    }

    @Override
    public void onOrderReceived(@NonNull IOrder order) {
        orders.add(order);
        print();
    }


    @Override
    public int getServiceType() {
        return Constants.USER_TYPE_KITCHEN_PRINTER;
    }
}
