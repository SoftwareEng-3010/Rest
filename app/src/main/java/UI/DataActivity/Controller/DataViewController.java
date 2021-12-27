package UI.DataActivity.Controller;

import java.util.List;
import java.util.Map;

import BusinessEntities.Address;
import BusinessEntities.Item;

public interface DataViewController {

    public void onRestaurantEditFinished(String restaurantName);
    public void onBranchEditFinished(Address address, boolean isKosher);
    public void onMenuEditFinished(List<Item> menuItems);
//    public void onEntityEditComplete(Map<String, String> dataMap);

}
