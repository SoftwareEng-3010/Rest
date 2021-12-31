package API;

import androidx.annotation.NonNull;

import API.Models.IOrder;

public interface IOrderHandler {

    public void onOrderReceived(@NonNull IOrder order);
}
