package API.Views;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.List;

import API.IOrderListener;
import API.Models.IServiceUnit;
import BusinessEntities.Branch;
import BusinessEntities.Table;

public interface IManagementView {

    public void loadKitchenFragment();
    public void loadServiceFragment();
    public void loadTableDetailsFragment(@NonNull Table table);
    public void loadHomeFragment();

    public void onDataFailure(String message);

    public List<IServiceUnit> getServiceUnits();
}