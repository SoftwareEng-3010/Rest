package UI.DataActivity.Controller;

import java.util.List;
import java.util.Map;

import BusinessEntities.Address;
import BusinessEntities.Item;
import BusinessEntities.Table;

public interface DataViewController {

    public void onRestaurantEditFinished(String restaurantName);
    public void onBranchEditFinished(Address address, boolean isKosher);
    public void onMenuEditFinished(List<Item> menuItems);
    public void onTablesEditFinished(List<Table> tables);
//    public void onEntityEditComplete(Map<String, String> dataMap);

}
