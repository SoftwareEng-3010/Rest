package BusinessEntities;

import com.google.firebase.firestore.PropertyName;

public class Table {

    @PropertyName("table_number")
    private int tableNumber;
    private int capacity;
    private double billAmount;
    @PropertyName("isOccupied")
    private boolean isOccupied;
    @PropertyName("isInside")
    private boolean isInside;

    public Table() {
        // Empty constructor required by Firebase method .toObject()
    }

    public Table(int tableNumber, int capacity, double billAmount,
                 boolean isOccupied, boolean isInside) {
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.billAmount = billAmount;
        this.isOccupied = isOccupied;
        this.isInside = isInside;
    }

    public Table(Table other) {
        this.billAmount = other.billAmount;
        this.tableNumber = other.tableNumber;
        this.capacity = other.capacity;
        this.isInside = other.isInside;
        this.isOccupied = other.isOccupied;
    }

    public int getTableNumber() {
        return tableNumber;
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
                "tableIndex=" + tableNumber +
                ", capacity=" + capacity +
                ", billAmount=" + billAmount +
                ", isOccupied=" + isOccupied +
                ", isInside=" + isInside +
                '}';
    }
}