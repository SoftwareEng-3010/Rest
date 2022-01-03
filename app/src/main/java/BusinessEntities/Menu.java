package BusinessEntities;

import com.google.firebase.firestore.PropertyName;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    @PropertyName("menu")
    private List<Item> menuItems;

    public Menu(){
        // Empty constructor required by Firebase method .toObject()
    }

    public Menu(List<Item> items) {
        this.menuItems = new ArrayList<>(items);
    }

    public Menu(Menu other) {
        this.menuItems = new ArrayList<>(menuItems);
    }

    @PropertyName("menu")
    public List<Item> getMenuItems() {
        return menuItems;
    }

    @Override
    public String toString() {
        return "Menu{--------------------------------------------------" +
                "items=" + menuItems +
                "------------------------------------------------------}";
    }
}
