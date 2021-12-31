package BusinessEntities;

public class QRCode {

    private String restaurantId;
    private String branchId;
    private int tableNum;

    public static final String KEY_RESTAURANT_ID = "restaurant_id";
    public static final String KEY_BRANCH_ID = "branch_id";
    public static final String KEY_TABLE_NUMBER = "table_num";

    public QRCode(String restaurant_id, String branch_id, int table_num) {
        this.restaurantId = restaurant_id;
        this.branchId = branch_id;
        this.tableNum = table_num;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getBranchId() {
        return branchId;
    }

    public int getTableNumber() {
        return tableNum;
    }
}
