package UI.DataActivity.View;

import android.os.Bundle;

import java.util.List;
import java.util.Map;

import BusinessEntities.Address;
import BusinessEntities.Branch;
import BusinessEntities.Item;
import BusinessEntities.Restaurant;
import BusinessEntities.Table;

public interface DataEditView {

    public void onRestaurantEditFinished(boolean isSuccessful, String restaurantName);
    public void onBranchEditFinished(boolean isSuccessful, Address address, boolean isKosher);
    public void onMenuEditFinished(boolean isSuccessful, List<Item> menuItems);
    public void onTablesEditFinished(boolean isSuccessful, List<Table> tables);

    public void onDataEditFinish(Restaurant restaurant, Branch branch);
}