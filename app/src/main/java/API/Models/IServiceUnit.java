package API.Models;

import androidx.annotation.NonNull;

import java.util.List;

public interface IServiceUnit extends IOrderListener {

    public List<IOrder> getOrders();
    public void update(@NonNull String message);
    public void onOrderReceived(@NonNull IOrder order);
//    public IOrderManager getController();

    public int getServiceType();
}
