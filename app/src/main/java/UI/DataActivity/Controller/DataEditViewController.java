package UI.DataActivity.Controller;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import BusinessEntities.Address;
import BusinessEntities.Branch;
import BusinessEntities.Item;
import BusinessEntities.Restaurant;
import BusinessEntities.Table;
import DataAccessLayer.RestDB;
import UI.DataActivity.View.DataEditView;

public class DataEditViewController implements DataViewController{

    private DataEditView view;

    private String restaurantName;
    private Address branchAddress;
    private boolean isKosher;
    private List<Item> itemList;
    private List<Table> tables;

    public DataEditViewController(DataEditView dataEditView) {
        this.view = dataEditView;
    }

    @Override
    public void onRestaurantEditFinished(String restaurantName) {

        // if (database already contains a restaurant named `restaurantName`...) {}

        if (restaurantName != null) {
            this.restaurantName = restaurantName;
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

        this.branchAddress = address;
        this.isKosher = isKosher;
        view.onBranchEditFinished(true, address, isKosher);
    }

    @Override
    public void onMenuEditFinished(List<Item> menuItems) {
        if (menuItems.size() > 0) {
            this.itemList = new ArrayList<>(menuItems);
            view.onMenuEditFinished(true, menuItems);
        }
        else {
            view.onMenuEditFinished(false, menuItems);
        }
    }

    @Override
    public void onTablesEditFinished(List<Table> tables) {
        if (tables.size() > 0) {
            this.tables = tables;
            view.onTablesEditFinished(true, tables);
            onDataEditFinished();
        }
        else {
            view.onTablesEditFinished(false, tables);
        }
    }

    public void onDataEditFinished() {

        Restaurant restaurant = new Restaurant(restaurantName);

//        RestDB.getInstance()



        Branch branch = new Branch(this.branchAddress, isKosher, null, this.tables);
    }
}
