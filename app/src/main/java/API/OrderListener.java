package API;

import androidx.annotation.NonNull;

import API.BusinessEntitiesInterface.IOrder;

public interface OrderListener {

    public void onOrderReceived(@NonNull IOrder order);
}
