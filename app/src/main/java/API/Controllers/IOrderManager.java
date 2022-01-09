package API.Controllers;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import API.Models.IServiceUnit;

public interface IOrderManager extends EventListener<QuerySnapshot> {

    public List<IServiceUnit> getSubscribedServiceUnits();

    public void detachSnapshotListener();

    public boolean registerUnit(@NonNull IServiceUnit unit);

    public boolean updateServiceUnit(@NonNull IServiceUnit unit);

    public void onOrderComplete(@NonNull String orderId);

    public void updateSubscribers(@NonNull String message);

}
