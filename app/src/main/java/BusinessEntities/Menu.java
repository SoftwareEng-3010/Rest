package BusinessEntities;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<Item> items;

    public Menu(){
        // Empty constructor required by Firebase method .toObject()
    }

    public Menu(List<Item> items) {
        this.items = new ArrayList<>(items);
    }

    public Menu(Menu other) {
        this.items = new ArrayList<>(items);
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Menu{--------------------------------------------------" +
                "items=" + items +
                "------------------------------------------------------}";
    }
}
