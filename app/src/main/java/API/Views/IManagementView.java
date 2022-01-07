package API.Views;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.List;

import API.Controllers.IServiceViewController;
import API.IOrderListener;
import API.Models.IOrder;
import API.Models.IServiceUnit;
import BusinessEntities.Branch;
import BusinessEntities.Table;

public interface IManagementView {

    public void loadHomeFragment();
    public void loadServiceFragment();
    public void loadKitchenFragment();
    public void loadQRActivity();

    public void onDataFailure(String message);

    public List<IServiceUnit> getServiceUnits();
    public List<IOrderListener> getOrderListeners();
}