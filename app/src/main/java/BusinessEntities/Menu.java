package BusinessEntities;

import java.util.ArrayList;
import java.util.List;

public class Menu {


    private List<Item> menu;

    public Menu(){
        // Empty constructor required by Firebase method .toObject()
    }

    public Menu(List<Item> items) {
        this.menu = new ArrayList<>(items);
    }

    public Menu(Menu other) {
        this.menu = new ArrayList<>(menu);
    }

    public List<Item> getMenu() {
        return menu;
    }

    @Override
    public String toString() {
        return "Menu{--------------------------------------------------" +
                "items=" + menu +
                "------------------------------------------------------}";
    }
}
