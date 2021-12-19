package BusinessLogic;

import android.util.Log;

import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import BusinessEntities.Address;
import BusinessEntities.QRCode;
import DataAccessLayer.RemoteRestDB;

/**
 * QRReader class will handle the validation and parsing of
 * scanned QRCodes.
 * Class methods are static so no instance of the class should be created
 * and
 */
public class QRReader {

    private static final String TAG = "QRReader";

//    private static final String SELECTED_RESTAURANT_INDEX  = "restaurant_index";
//    private static final String SELECTED_BRANCH_INDEX = "branch_index";
//    private static final String SELECTED_TABLE_INDEX = "table_index";

//    private static final RemoteRestDB restDB =
//            RemoteRestDB.getInstance(); // Database reference, statically initialized

    /**
     * A private empty constructor to prevent object instantiation
     */
    private QRReader() {}

    /**
     * Main functionality of the class:
     * This method validates and parses a `Result` object.
     * A valid QRCode for our app contains a JSON object,
     * which contains 3 keys:
     * 1. Restaurant index (Relative to our database restaurant indexing)
     * 2. Branch index (Relative in the same way)
     * 3. Table index (The table number in which the client scanned the QRCode from)
     *
     * @param result - An object from `Budiyev`s dependency for our QRCode scanner.
     *                 It contains the information contained in the QRCode.
     * @return - An array of `int`s with the specified data values (Integers) in our specific order.
     * @throws Exception - If something went wrong while parsing from string to json,
     *                      or, if the data does not correspond to our database current data.
     */
    public static QRCode readFromResult(Result result) throws Exception {

        try {
            // QRCode should contain a JSON formatted `String`
            JSONObject json = getJsonObject(result.getText());

            Log.e(TAG, "Got JSON Object" + result.getText());

            // Parse data from JSON
            String restaurantId = json.getString(QRCode.KEY_RESTAURANT_ID);

            JSONObject addressJson = getJsonObject(json.getString(QRCode.KEY_BRANCH_ADDRESS));
            Address branchAddress = new Address(addressJson);

            int tableIndex = json.getInt(QRCode.KEY_TABLE_NUMBER);

            // Return an array with the relevant data to display the menu of the given
            // branch and restaurant
            return new QRCode(restaurantId, branchAddress, tableIndex);
        }
        catch (Exception e) {
            Log.e(TAG, e.getMessage());
            throw e;
        }
    }

    private static JSONObject getJsonObject(String jsonString) throws Exception{

        try {
            JSONObject json = new JSONObject(jsonString);
//            Log.e(TAG, json.toString());
            return json;
        }
        catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            throw e;
        }
    }
}
