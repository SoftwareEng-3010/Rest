package BusinessEntities;

import java.util.List;

import UIAdapters.BranchSmallViewModel;

public class Branch extends BranchSmallViewModel {

    /**
     * Protected fields are passed by inheritance.
     *         protected Address address;
     *         protected int id;
     *         protected boolean isKosher;
     *         protected boolean isOpen;
     */

    // Private fields
    protected List<Item> menu;
    protected List<Table> tables;

    public Branch(Address address, int id, boolean isKosher, boolean isOpen,
                  List<Item> menu, List<Table> tables) {
        super(address, id, isKosher, isOpen);

        this.menu = menu;
        this.tables = tables;
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
                ", menu=" + menu +
                ", tables=" + tables +
                '}';
    }
}
