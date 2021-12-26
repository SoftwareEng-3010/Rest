package UI.DataActivity.Controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import java.util.List;
import java.util.Map;

import BusinessEntities.Address;
import BusinessEntities.Branch;
import BusinessEntities.Item;
import BusinessEntities.Menu;
import BusinessEntities.Restaurant;
import UI.DataActivity.View.DataEditSubView;
import UI.DataActivity.View.DataEditView;

public class DataEditViewController implements DataViewController{

    private DataEditView view;

    private Restaurant restaurantToBuild;
    private Branch branchToBuild;
    private List<Item> menuItems;

    private List<DataEditSubView> fragments;
    private int fragmentIndex;

    private Bundle dataBundle;

    public DataEditViewController(DataEditView dataEditView, List<DataEditSubView> fragments) {
        this.view = dataEditView;
        this.fragments = fragments;
        dataBundle = new Bundle();
        fragmentIndex = 0;
    }

    private void move(){
        if (fragmentIndex < fragments.size())
            this.fragments.get(fragmentIndex++).nextFragment(dataBundle);
        else
            this.view.onDataEditFinished(true, "Data was successfully written to DB");
    }

    @Override
    public void onRestaurantEditFinished(String restaurantName) {

        // if (database already contains a restaurant named `restaurantName`...) {}

        // Should change `rest_name` with some constant
        dataBundle.putString("rest_name", restaurantName);
        move();
    }

    @Override
    public void onBranchEditFinished(Address address, boolean isKosher) {
        if (address == null)
            return;

        dataBundle.putString("branch_city", address.getCity());
        dataBundle.putString("branch_street", address.getStreet());
        dataBundle.putString("branch_building_number", address.getBuildingNumber());
        dataBundle.putBoolean("branch_is_kosher", isKosher);
        move();
    }

    @Override
    public void onMenuEditFinished(List<Item> menuItems) {
        move();
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
