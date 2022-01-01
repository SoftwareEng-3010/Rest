package UI.DataActivity.View;

import android.os.Bundle;

import java.util.List;
import java.util.Map;

import BusinessEntities.Address;
import BusinessEntities.Item;

public interface DataEditView {

    public void onRestaurantEditFinished(boolean isSuccessful, String restaurantName);
    public void onBranchEditFinished(boolean isSuccessful, Address address, boolean isKosher);
    public void onMenuEditFinished(boolean isSuccessful, List<Item> menuItems);
}