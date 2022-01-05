package BusinessEntities;

import com.google.firebase.firestore.PropertyName;

public class Table {

    @PropertyName("table_number")
    private int tableNumber;
    private int capacity;
    @PropertyName("is_occupied")
    private boolean isOccupied;
    @PropertyName("is_inside")
    private boolean isInside;
    private Bill bill;

    public Table() {
        // Empty constructor required by Firebase method .toObject()
    }

    public Table(int tableNumber, int capacity, Bill bill,
                 boolean isOccupied, boolean isInside) {
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.bill = bill;
        this.isOccupied = isOccupied;
        this.isInside = isInside;
    }

    public Table(Table other) {
        this.bill = new Bill(other.bill);
        this.tableNumber = other.tableNumber;
        this.capacity = other.capacity;
        this.isInside = other.isInside;
        this.isOccupied = other.isOccupied;
    }

    @PropertyName("table_number")
    public int getTableNumber() {
        return tableNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getBillTotal() {
        return this.bill.getTotal();
    }

    @PropertyName("is_occupied")
    public boolean isOccupied() {
        return isOccupied;
    }

    @PropertyName("is_inside")
    public boolean isInside() {
        return isInside;
    }

    public Bill getBill(){ return bill; }

    public void setBill(Bill bill){
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableIndex=" + tableNumber +
                ", capacity=" + capacity +
                ", billAmount=" + bill.getTotal() +
                ", isOccupied=" + isOccupied +
                ", isInside=" + isInside +
                '}';
    }
}