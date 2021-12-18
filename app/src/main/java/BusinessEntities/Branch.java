package BusinessEntities;

import com.google.firebase.firestore.PropertyName;

import java.util.List;

public class Branch {

    // ------- Branch info ----------
    private Address address;
    private int id;
    @PropertyName("menu")
    private List<Item> menu;
    private Menu menuObj;
    private List<Table> tables;
    @PropertyName("isKosher")
    private boolean isKosher;
    // More will be added ...
//    private boolean isOpen;
    // ------------------------------
    public Branch() {
        // Empty constructor is required by Firebase method .toObject()
    }

    public Branch(Address address, int id, boolean isKosher/*, boolean isOpen*/, List<Item> menu, List<Table> tables) {
        this.address = address;
        this.id = id;
        this.isKosher = isKosher;
        this.menu = menu;
        this.menuObj = new Menu(menu);
        this.tables = tables;
    }

    public Address getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    @PropertyName("isKosher")
    public boolean isKosher() {
        return isKosher;
    }

    public Menu getMenu() {
        return menuObj;
    }

    public List<Table> getTables() {
        return tables;
    }

    @Override
    public String toString() {
        return "\nBranch{" +
                "address=" + address +
                ", id=" + id +
                ", isKosher=" + isKosher +
                ",\n menu=" + menuObj +
                ",\n tables=" + tables +
                "}\n";
    }
}
