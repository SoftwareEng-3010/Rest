package BusinessEntities;

import java.util.ArrayList;
import java.util.List;

import API.Models.IOrder;

public class Bill {

    private final double EMPTY_BILL = 0.0;

    private List<IOrder> orders;
    private double total;

    public Bill(){
        orders = new ArrayList<>();
        total = EMPTY_BILL;
    }

    public Bill(Bill other){
<<<<<<< HEAD
         this.orders = new ArrayList<>(other.getOrders());
         this.total = other.getTotal();
=======
        this.orders = new ArrayList<>(other.getOrders());
        this.total = other.getTotal();
>>>>>>> 050d7555a5e4146497b9a2c00479eaa87ed717d6
    }

    public void addOrder(IOrder order){
        orders.add(order);
        for(Item item : order.getOrderItems()){
            total += item.getPrice();
        }
    }

    public void removeOrder(String orderId){
        for (IOrder order : orders){
            if(order.getDocId() == orderId){
                orders.remove(order);
                for(Item item : order.getOrderItems()){
                    total -= item.getPrice();
                }
            }
        }
    }

    public double getTotal(){
        return this.total;
    }

    public List<IOrder> getOrders(){
        return this.orders;
    }

    public String toString(){
        String res = "";
        for (IOrder order : orders){
            for(Item item : order.getOrderItems()){
                res += item.toString() + '\n';
            }
        }
        res += "Total Amount:\t" + Double.toString(total);
        return res;
    }
}