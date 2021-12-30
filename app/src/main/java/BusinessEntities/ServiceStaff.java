package BusinessEntities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.List;

import API.Models.IOrder;
import API.Models.IServiceUnit;
import API.Constants.Constants;

public class ServiceStaff implements IServiceUnit {

    private List<IOrder> orders;
    private String uid;
    private String email = null;
    private final int type = Constants.USER_TYPE_SERVICE;


    public ServiceStaff() {
//        type = Constants.TYPE_SERVICE_STAFF_USER;
    }

    @Override
    public List<IOrder> getOrders() {
        return null;
    }

    @Override
    public void update(String message) {

    }

    @Override
    public void onOrderReceived(@NonNull IOrder order) {

    }
}
