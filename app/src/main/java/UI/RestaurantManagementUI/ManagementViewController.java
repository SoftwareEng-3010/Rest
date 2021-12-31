package UI.RestaurantManagementUI;

import androidx.annotation.Nullable;

import API.Database.Database;
import API.Database.DatabaseRequestCallback;
import BusinessEntities.Branch;
import DataAccessLayer.RestDB;

public class ManagementViewController implements IManagementViewController{

    private IManagementView managementView;
    private String branchId;
    private String restId;

    private Database db;
    private Branch branch;

    public ManagementViewController(IManagementView view, String restId, String branchUid) {
        this.managementView = view;
        this.branchId = branchUid;
        this.restId = restId;

        db = RestDB.getInstance();
        db.getBranch(restId, branchUid, new DatabaseRequestCallback() {
            @Override
            public void onObjectReturnedFromDB(@Nullable Object obj) {
                if (obj == null) {
                    view.onDataFailure("Branch object came back null from database (Branch_UID: " + branchUid + ")");
                }

                else {
                    branch = (Branch) obj;
                    view.beforeMoveToHomeScreen(branch);
                }
            }
        });
    }

    @Override
    public void onHomeButtonClicked() {

    }

    @Override
    public void onServiceButtonClicked() {

    }

    @Override
    public void onKitchenButtonClicked() {

    }

    private void loadHomeScreenData() {

    }
}
