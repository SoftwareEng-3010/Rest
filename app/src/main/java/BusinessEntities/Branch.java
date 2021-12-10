package BusinessEntities;

import java.util.ArrayList;
import java.util.HashMap;

public class Branch {

    private HashMap<String, String> address;
    private int id;
    private boolean isKosher;
    private ArrayList<Item> menu;

    public Branch(HashMap<String, String> address, int id, boolean isKosher, ArrayList<Item> menu) {
        this.address = address;
        this.id = id;
        this.isKosher = isKosher;
        this.menu = menu;
    }

    public HashMap<String, String> getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public boolean isKosher() {
        return isKosher;
    }

    public ArrayList<Item> getMenu() {
        return menu;
    }

    public void setAddress(HashMap<String, String> address) {
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKosher(boolean kosher) {
        isKosher = kosher;
    }

    public void setMenu(ArrayList<Item> menu) {
        this.menu = menu;
    }
}
