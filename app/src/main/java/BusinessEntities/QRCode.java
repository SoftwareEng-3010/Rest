package BusinessEntities;

public class QRCode {

    private String restaurantId;
    private Address branchAddress;
    private int tableNum;

    public static final String KEY_RESTAURANT_ID = "restaurant_id";
    public static final String KEY_BRANCH_ADDRESS = "branch_address";
    public static final String KEY_TABLE_NUMBER = "table_num";

    public QRCode(String restaurant_id, Address branch_address, int table_num) {
        this.restaurantId = restaurant_id;
        this.branchAddress = branch_address;
        this.tableNum = table_num;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public Address getBranchAddress() {
        return branchAddress;
    }

    public int getTableNumber() {
        return tableNum;
    }
}
