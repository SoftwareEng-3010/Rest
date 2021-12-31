package API.Views;

import androidx.fragment.app.Fragment;

import BusinessEntities.Branch;

public interface IManagementView {

    public void loadFragment(Fragment fragment);

    public void onDataFailure(String message);
}