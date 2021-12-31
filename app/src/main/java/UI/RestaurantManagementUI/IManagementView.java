package UI.RestaurantManagementUI;

import BusinessEntities.Branch;

public interface IManagementView {

    public void beforeMoveToHomeScreen(Branch branch);
    public void beforeMoveToServiceScreen();
    public void beforeMoveToKitchen();

    public void onDataFailure(String message);
}