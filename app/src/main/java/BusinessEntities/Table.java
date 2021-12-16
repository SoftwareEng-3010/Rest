package BusinessEntities;

public class Table {

    private int tableIndex;
    private int capacity;
    private double billAmount;
    private boolean isOccupied;
    private boolean isInside;

    public Table(int tableIndex, int capacity, double billAmount,
                 boolean isOccupied, boolean isInside) {
        this.tableIndex = tableIndex;
        this.capacity = capacity;
        this.billAmount = billAmount;
        this.isOccupied = isOccupied;
        this.isInside = isInside;
    }

    public int getTableIndex() {
        return tableIndex;
    }

    public void setTableIndex(int tableIndex) {
        this.tableIndex = tableIndex;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public boolean isInside() {
        return isInside;
    }

    public void setInside(boolean inside) {
        isInside = inside;
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