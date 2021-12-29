package API.BusinessEntitiesInterface;

import com.google.firebase.firestore.EventListener;

import java.util.List;

import API.OrderListener;

public interface IOrderController extends EventListener /* This implementation serves as a firestore callback method*/{

    public List<IServiceUnit> getSubscribedServiceUnits();

    public boolean updateServiceUnit(IServiceUnit unit);
}
