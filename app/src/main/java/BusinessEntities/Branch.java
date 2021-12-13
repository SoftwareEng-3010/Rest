package BusinessEntities;

import com.google.firebase.firestore.PropertyName;

import java.util.ArrayList;
import java.util.HashMap;

public class Branch {

    // private fields
    // TODO: 12/13/2021 change address field to simple string instead of map, also in firestore
    private HashMap<String, String> address;
    private int id;
    private boolean isKosher;
    private ArrayList<Item> menu;

    // empty constructor for deserializing Firestore document
    public Branch(){}

    public Branch(HashMap<String, String> address, int id, boolean isKosher, ArrayList<Item> menu) {
        this.address = address;
        this.id = id;
        this.isKosher = isKosher;
        this.menu = menu;
    }

    // setters & getters
    public void setAddress(HashMap<String, String> address) {
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PropertyName("isKosher")
    public void setIsKosher(boolean isKosher) {
        this.isKosher = isKosher;
    }

    public void setMenu(ArrayList<Item> menu) {
        this.menu = menu;
    }

    public HashMap<String, String> getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    @PropertyName("isKosher")
    public boolean getIsKosher() {
        return isKosher;
    }

    public ArrayList<Item> getMenu() {
        return menu;
    }

    // A String representation of a Restaurant object
    @Override
    public String toString(){
        String res = "";
        res += "Branch id: " + this.id + '\n' +
                "Branch kosher status: " + this.isKosher + '\n';
        for(String s : this.address.values()){
            res += ' ' + s + ' ';
        }
        return res + '\n';
    }
}
