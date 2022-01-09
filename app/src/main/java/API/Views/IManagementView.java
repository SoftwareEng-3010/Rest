package API.Views;

import java.util.List;

import API.Models.IOrderListener;
import API.Models.IServiceUnit;

public interface IManagementView {

    public void loadHomeFragment();
    public void loadServiceFragment();
    public void loadKitchenFragment();
    public void loadQRActivity();

    public void onDataFailure(String message);

    public List<IServiceUnit> getServiceUnits();
    public List<IOrderListener> getOrderListeners();
}