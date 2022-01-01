package BusinessEntities;

import java.util.ArrayList;
import java.util.List;

public class Bill {

    private final double EMPTY_BILL = 0.0;

    private List<Order> orders;
    private double amount;

    public Bill(){
        orders = new ArrayList<>();
        double amount = EMPTY_BILL;
    }

    public void addOrder(Order order){
        orders.add(order);
        for(Item item : order.getOrderItems()){
            amount += item.getPrice();
        }
    }

    public void removeOrder(String orderId){
        for (Order order : orders){
            if(order.getOrderId() == orderId){
                orders.remove(order);
                for(Item item : order.getOrderItems()){
                    amount -= item.getPrice();
                }
            }
        }
    }

    public String toString(){
        String res = "";
        for (Order order : orders){
            for(Item item : order.getOrderItems()){
                res += item.toString() + '\n';
            }
        }
        res += "Total Amount:\t" + Double.toString(amount);
        return res;
    }
}