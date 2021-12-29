package API.BusinessEntitiesInterface;

import androidx.annotation.NonNull;

import java.util.List;

import API.BusinessEntitiesInterface.Auth.IUser;
import API.OrderListener;
import BusinessEntities.Item;

public interface IServiceUnit extends OrderListener {

    public List<IOrder> getOrders();
}
