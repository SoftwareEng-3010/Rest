package BusinessLogic;

import android.util.Log;

import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import BusinessEntities.Branch;
import BusinessEntities.Restaurant;
import DataAccessLayer.RestDB;

/**
 * QRReader class will handle the validation and parsing of
 * scanned QRCodes.
 * Class methods are static so no instance of the class should be created
 * and
 */
public class QRReader {

    private static final String TAG = "QRReader";

    private static final String SELECTED_RESTAURANT_INDEX  = "restaurant_index";
    private static final String SELECTED_BRANCH_INDEX = "branch_index";
    private static final String SELECTED_TABLE_INDEX = "table_index";

    private static final RestDB restDB =
            RestDB.getInstance(); // Database reference, statically initialized

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
    public static int[] readQRResult(Result result) throws Exception {

        try {
            // QRCode should contain a JSON formatted `String`
            JSONObject json = getJsonObject(result.getText());

            if (!isValidQRCode(result))
                // If isValidQRCode() did not throw but returned false:
                throw new Exception("Error in class QRReader: The QRCode is Invalid");

            // Parse data from JSON
            int restID = json.getInt(SELECTED_RESTAURANT_INDEX);
            int branchID = json.getInt(SELECTED_BRANCH_INDEX);
            int tableIndex = json.getInt(SELECTED_TABLE_INDEX);

            // Return an array with the relevant data to display menu at specified branchID
            return new int[] {restID, branchID, tableIndex};
        }
        catch (Exception e) {
            Log.e(TAG, e.getMessage());
            throw e;
        }
    }

    /**
     * This method checks if a given `Result` object
     * refers to a valid QRCode, which was scanned by the user.
     * A valid QRCode for this application stores a JSON Object
     * with relevant fields for finding the correct restaurant branch
     * and the correct table inside the branch.
     * @param result - An object from `Budiyev`s dependency that holds the result of a
     *               scanned QRCode.
     * @return - If the QRCode is valid for our application return true.
     *           Otherwise - return false.
     */
    public static boolean isValidQRCode(Result result) throws Exception {
        if (result == null) return false;

        try {

            JSONObject json = getJsonObject(result.getText());

            // Parse relevant JSON keys. If no such key is found - an exception will be thrown.
            int restID = json.getInt(SELECTED_RESTAURANT_INDEX);
            int branchID = json.getInt(SELECTED_BRANCH_INDEX);
            int tableIndex = json.getInt(SELECTED_TABLE_INDEX);

            // Make sure the values are valid as well (Not exceeding table number or whatever)
            if (restID >= restDB.getRestaurants().size()) return false;
            Restaurant restaurant = restDB.getRestaurants().get(restID);
            if (branchID >= restaurant.getNumOfBranches()) return false;
            Branch branch = restaurant.getBranches().get(branchID);

            // Check about Table information in the QRCode
            // The JSON object seems to be fine
            return true;

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            throw e;
        }
    }

    private static JSONObject getJsonObject(String jsonString) throws Exception{

        try {
            JSONObject json = new JSONObject(jsonString);
            Log.e(TAG, json.toString());
            return json;
        }
        catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            throw e;
        }
    }
}
