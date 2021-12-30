package API;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.EventListener;

import API.Models.IOrder;

public interface IOrderListener /* This implementation serves as a Firestore callback method*/ {

    public void onOrderReceived(@NonNull IOrder order);
}
