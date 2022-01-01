package BusinessEntities;

import android.util.Log;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.PropertyName;

import java.util.ArrayList;
import java.util.List;

import API.Constants.Constants;

public class Branch {

    // ------- Branch info ----------
    @DocumentId
    private String docId;
    private Address address;
    private List<Table> tables;
    @PropertyName("menu_path")
    private String menuPath;
    @PropertyName("isKosher")
    private boolean isKosher;
    private List<Integer> service_units;
    // ------------------------------

    public Branch() {
        // Empty constructor is required by Firebase method .toObject()
    }

    public Branch(Address address, boolean isKosher, String menuPath, List<Table> tables) {
        this.address = address;
        this.isKosher = isKosher;
        this.tables = new ArrayList<>(tables);
        this.menuPath = menuPath;
        this.service_units = new ArrayList<>();
    }

    public Branch(Branch other) {
        this.address = other.address; // Has a copy constructor
        this.isKosher = other.isKosher;
    }

    public void addServiceUnit(int serviceUnitType) {
        if (service_units == null) service_units = new ArrayList<>();
        switch (serviceUnitType) {

            case Constants
                    .USER_TYPE_SERVICE:
                this.service_units.add(serviceUnitType);
                break;
            case Constants.USER_TYPE_KITCHEN:
                this.service_units.add(serviceUnitType);
                break;

            default:
                Log.e("BranchClass", "serviceUnitType == " + serviceUnitType + ", while there are" +
                        " only 3(Service), 4(Kitchen)");

        }
    }

    public String getDocId() {
        if (docId == null)
            return "(ERROR IN CLASS BRANCH) Branch with address: " + this.address + ", does not have its docId field initialized";
        return docId;
    }

    public Address getAddress() {
        return address;
    }

    @PropertyName("menu_path")
    public String getMenuPath() { return menuPath; }

    @PropertyName("isKosher")
    public boolean isKosher() {
        return isKosher;
    }

    public List<Table> getTables() {
        return tables;
    }

    @Override
    public String toString() {
        return "\nBranch{\n" +
                "address=" + address +
                ", isKosher=" + isKosher +
                ",\n menu=" + menuPath +
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
