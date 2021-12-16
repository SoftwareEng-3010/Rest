package BusinessEntities;

import java.util.List;

public class Branch {


    // Private fields
    private Address address;
    private int id;
    private boolean isKosher;
    private boolean isOpen;
    private List<Item> menu;
    private List<Table> tables;

    public Branch(Address address, int id, boolean isKosher, boolean isOpen, List<Item> menu, List<Table> tables) {
        this.address = address;
        this.id = id;
        this.isKosher = isKosher;
        this.isOpen = isOpen;
        this.menu = menu;
        this.tables = tables;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isKosher() {
        return isKosher;
    }

    public void setKosher(boolean kosher) {
        isKosher = kosher;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public List<Item> getMenu() {
        return menu;
    }

    public void setMenu(List<Item> menu) {
        this.menu = menu;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "address=" + address +
                ", id=" + id +
                ", isKosher=" + isKosher +
                ", isOpen=" + isOpen +
                ", menu=" + menu +
                ", tables=" + tables +
                '}';
    }
}
