package BusinessEntities;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.PropertyName;

import java.util.List;

public class Branch {

    // ------- Branch info ----------
    @DocumentId
    private String docId;
    private Address address;
    private String menuId;
    private List<Table> tables;

    @PropertyName("isKosher")
    private boolean isKosher;

    // More will be added ...
//    private boolean isOpen;
//    private Menu menuObj;
    // ------------------------------
    public Branch() {
        // Empty constructor is required by Firebase method .toObject()
    }
    public Branch(Address address, String id, boolean isKosher/*, boolean isOpen*/, String menuId, List<Table> tables) {
        this.address = address;
        this.isKosher = isKosher;
        this.menuId = menuId;
        this.tables = tables;
    }

    public Branch(Branch other) {
        this.address = other.address; // Has a copy constructor
        this.isKosher = other.isKosher;
        this.menuId = other.menuId;

    }

    public String getDocId() {
        return docId;
    }

    public Address getAddress() {
        return address;
    }

    public String getMenuId() {
        return menuId;
    }

    @PropertyName("isKosher")
    public boolean isKosher() {
        return isKosher;
    }

//    public Menu getMenu() {
//        return menuObj;
//    }

    public List<Table> getTables() {
        return tables;
    }

    @Override
    public String toString() {
        return "\nBranch{\n" +
                "address=" + address +
                ", isKosher=" + isKosher +
                ",\n menu=" + menuId +
                ",\n tables=" + tables +
                "\n}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return address.equals(branch.address);
    }
}
