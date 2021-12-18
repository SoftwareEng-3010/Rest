package BusinessEntities;

import com.google.firebase.firestore.PropertyName;

public class Table {

    private int tableIndex;
    private int capacity;
    private double billAmount;
    @PropertyName("isOccupied")
    private boolean isOccupied;
    @PropertyName("isInside")
    private boolean isInside;

    public Table() {
        // Empty constructor required by Firebase method .toObject()
    }

    public Table(int tableIndex, int capacity, double billAmount,
                 boolean isOccupied, boolean isInside) {
        this.tableIndex = tableIndex;
        this.capacity = capacity;
        this.billAmount = billAmount;
        this.isOccupied = isOccupied;
        this.isInside = isInside;
    }

    public Table(Table other) {
        this.billAmount = other.billAmount;
        this.tableIndex = other.tableIndex;
        this.capacity = other.capacity;
        this.isInside = other.isInside;
        this.isOccupied = other.isOccupied;
    }

    public int getTableIndex() {
        return tableIndex;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getBillAmount() {
        return billAmount;
    }

    @PropertyName("isOccupied")
    public boolean isOccupied() {
        return isOccupied;
    }

    @PropertyName("isInside")
    public boolean isInside() {
        return isInside;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableIndex=" + tableIndex +
                ", capacity=" + capacity +
                ", billAmount=" + billAmount +
                ", isOccupied=" + isOccupied +
                ", isInside=" + isInside +
                '}';
    }
}