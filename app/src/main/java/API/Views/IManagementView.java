package API.Views;

import androidx.fragment.app.Fragment;

import java.util.List;

import API.Models.IServiceUnit;
import BusinessEntities.Branch;

public interface IManagementView {

    public void loadFragment(Fragment fragment);

    public void onDataFailure(String message);

    public List<IServiceUnit> getServiceUnits();
}