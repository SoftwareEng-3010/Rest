package BusinessLogic;

import androidx.annotation.NonNull;

import API.Controllers.IHomeViewController;
import API.Controllers.IManagementViewController;
import API.Views.IHomeView;
import API.Views.IManagementView;

public class HomeViewController implements IHomeViewController {

    private IManagementView managementView;
    private IHomeView homeView;
//    private IManagementViewController mainController;
    private String restId, branchId;

    public HomeViewController(@NonNull IManagementView managementView, @NonNull IHomeView homeView, String restId, String branchId) {
        this.homeView = homeView;
        this.restId = restId;
        this.branchId = branchId;
        this.managementView = managementView;
    }

    @Override
    public void onSwipeLeft() {
        homeView.startQRActivity();
    }

    @Override
    public void onSwipeRight() {
        homeView.loadServiceUI();
    }

}
