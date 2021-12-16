package UIAdapters;

import com.google.firebase.firestore.PropertyName;

import BusinessEntities.Address;

public class BranchSmallViewModel {

    protected Address address;
    protected int id;
    protected boolean isKosher;
    protected boolean isOpen;


    public BranchSmallViewModel(Address address, int id, boolean isKosher, boolean isOpen) {
        this.address = address;
        this.id = id;
        this.isKosher = isKosher;
        this.isOpen = isOpen;
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

    @PropertyName("isKosher")
    public boolean isKosher() {
        return isKosher;
    }

    @PropertyName("isKosher")
    public void setKosher(boolean kosher) {
        isKosher = kosher;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String toString() {
        return "BranchSmallViewModel{" +
                "address=" + address +
                ", id=" + id +
                ", isKosher=" + isKosher +
                ", isOpen=" + isOpen +
                '}';
    }
}
