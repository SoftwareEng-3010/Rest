package UI.DataActivity.Controller;

import android.os.Bundle;

import java.util.List;

import BusinessEntities.Address;
import BusinessEntities.Branch;
import BusinessEntities.Item;
import BusinessEntities.Restaurant;
import UI.DataActivity.View.DataEditView;

public class DataEditViewController implements DataViewController{

    private DataEditView view;

    private Restaurant restaurantToBuild;
    private Branch branchToBuild;
    private List<Item> menuItems;

    private Bundle dataBundle;

    public DataEditViewController(DataEditView dataEditView) {
        this.view = dataEditView;
        dataBundle = new Bundle();
    }

    @Override
    public void onRestaurantEditFinished(String restaurantName) {

        // if (database already contains a restaurant named `restaurantName`...) {}

        if (restaurantName != null) {
            view.onRestaurantEditFinished(true, restaurantName);
        }
        else {
            view.onRestaurantEditFinished(false, null);
        }
        // Should change `rest_name` with some constant
//        dataBundle.putString("rest_name", restaurantName);
    }

    @Override
    public void onBranchEditFinished(Address address, boolean isKosher) {
        if (address == null)
            return;

        view.onBranchEditFinished(true, address, isKosher);

//        dataBundle.putString("branch_city", address.getCity());
//        dataBundle.putString("branch_street", address.getStreet());
//        dataBundle.putString("branch_building_number", address.getBuildingNumber());
//        dataBundle.putBoolean("branch_is_kosher", isKosher);
    }

    @Override
    public void onMenuEditFinished(List<Item> menuItems) {
        if (menuItems.size() > 0)
            view.onMenuEditFinished(true, menuItems);
        else {
            view.onMenuEditFinished(false, menuItems);
        }
    }
//
//    @Override
//    public void onRestaurantEditFinished(String restaurantName) {
//
//        if (restaurantName == null || restaurantName.isEmpty())
//            view.onDataEditFinished(false, "Restaurant name is invalid!");
//
//        restaurantToBuild = new Restaurant(restaurantName, null);
//
//        view.onDataEditFinished(true, "Restaurant name is valid");
//    }
//
//    @Override
//    public void onBranchEditFinished(Address address, boolean isKosher) {
//        if (address == null)
//            view.onDataEditFinished(false, "Branch info is invalid");
//        branchToBuild = new Branch(address, isKosher, null, null);
//        restaurantToBuild.addBranch(branchToBuild);
//
//        view.onDataEditFinished(true, "Branch info is valid");
//    }
//
//    @Override
//    public void onMenuEditFinished(List<Item> menuItems) {
//
//        if (menuItems == null || menuItems.size() == 0)
//            view.onDataEditFinished(false, "Failed to write restaurant to database");
//
//        view.onDataEditFinished(true, "Menu info is valid!");
//    }

}
