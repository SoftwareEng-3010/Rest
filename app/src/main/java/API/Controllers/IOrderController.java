package API.Controllers;

import androidx.annotation.NonNull;

import java.util.List;

import API.Models.IServiceUnit;

public interface IOrderController {

    public List<IServiceUnit> getSubscribedServiceUnits();

    public boolean updateServiceUnit(@NonNull IServiceUnit unit);

    public void updateSubscribers(@NonNull String message);
}
