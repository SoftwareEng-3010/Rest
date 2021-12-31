package API.Models;

import java.util.List;

import API.IOrderHandler;
import API.IOrderListener;

public interface IServiceUnit extends IOrderHandler {

    public List<IOrder> getOrders();
    public void update(String message);
}
