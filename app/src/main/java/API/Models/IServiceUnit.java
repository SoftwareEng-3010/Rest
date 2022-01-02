package API.Models;

import androidx.annotation.NonNull;

import java.util.List;

import API.IOrderController;
import API.IOrderListener;

public interface IServiceUnit extends IOrderListener {

    public List<IOrder> getOrders();
    public IOrderController getController();
    public void update(@NonNull String message);
    public void onOrderReceived(@NonNull IOrder order);

    public int getServiceType();
}
