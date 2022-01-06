package BusinessLogic;

import androidx.annotation.NonNull;

import API.Controllers.IHomeViewController;
import API.Controllers.IManagementViewController;
import API.Views.IHomeView;

public class HomeViewController implements IHomeViewController {

    private IHomeView homeView;
    private IManagementViewController mainController;
    private String restId, branchId;

    public HomeViewController(@NonNull IHomeView homeView, String restId, String branchId) {
        this.homeView = homeView;
        this.restId = restId;
        this.branchId = branchId;
//        this.mainController = mainController;
    }

    @Override
    public void onSwipeLeft() {
        homeView.loadServiceUI();
    }

    @Override
    public void onSwipeRight() {

    }

    @Override
    public void onServiceButtonClicked() {
    }

}
