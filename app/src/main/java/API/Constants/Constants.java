package API.Constants;

import BusinessEntities.Address;
import BusinessEntities.QRCode;

public interface Constants {

    // Constant strings of our database documents:
    public final String DB_BRANCHES_COLLECTION_NAME = "branch";
    public final String DB_RESTAURANT_COLLECTION_NAME = "our_restaurants";
    public final String DB_MENU_FIELD_NAME = "menu_path";

    // Constant key names of our QRCode`s JSON object:
    public static final String KEY_RESTAURANT_ID = QRCode.KEY_RESTAURANT_ID;
    public static final String KEY_BRANCH_ID = QRCode.KEY_BRANCH_ID;
    public static final String KEY_TABLE_NUMBER = QRCode.KEY_TABLE_NUMBER;
    public static final String KEY_MENU_PATH = DB_MENU_FIELD_NAME;

    // Constant key names of our QRCode`s JSON object:
    public static final int ADDRESS_FIELD_CITY = Address.FIELD_CITY;
    public static final int ADDRESS_FIELD_STREET = Address.FIELD_STREET;
    public static final int ADDRESS_FIELD_BUILDING_NUMBER = Address.FIELD_BUILDING_NUMBER;

    public static final int USER_TYPE_CUSTOMER = 0;
    public static final int USER_TYPE_BRANCH_MANAGER = 1;
    public static final int USER_TYPE_BRANCH_MANAGER2 = 2;
    public static final int USER_TYPE_SERVICE = 3;
    public static final int USER_TYPE_KITCHEN = 4;
    public static final int USER_TYPE_OTHER_USERS = 5;
}
