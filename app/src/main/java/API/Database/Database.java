package API.Database;

import com.google.firebase.auth.FirebaseUser;

import BusinessEntities.Restaurant;

/**
 * This interface defines the behavior of our
 * `RestDB` object, which handles all CRUD database operations.
 */
public interface Database {

    // Read operations in our database:

    /**
     * Get a List<Branch> of all branches associated with a specific restaurant.
     *
     * @param restId - The document id of the required restaurant
     *
     * @param callBack - A callback method, returns the required object to the caller after
     *                 the object comes back from the Firestore Database
     */
    public void getBranches(String restId, OnDataReceivedFromDB callBack);

    /**
     * Get a `Branch` object from the database.
     *
     * @param branchId - The document id of the required branch
     *
     * @param callBack - A callback method, returns the required object to the caller after
     *                 the object comes back from the Firestore Database
     */
    public void getBranch(String branchId, OnDataReceivedFromDB callBack);

    /**
     * Get a `Menu` object from the database.
     * This method receives all possible parameters required to find a menu.
     * Either use restId + branchId,
     * Or use directly a menuPath.
     * @param restId - (String) A unique restaurant identifier
     * @param branchId - (String) A unique branch identifier
     * @param menuPath - (String) An explicit path for a menu.
     *                 For example: <collectionName>/<restId>/menus/CSGarr0tfBlOfOUGAga1
     * @param callBack - A callback method, returns the required object to the caller after
     *                 the object comes back from the Firestore Database
     */
    public void getMenu(String restId, String branchId, String menuPath, OnDataReceivedFromDB callBack);

    /**
     * Get a `Menu` object using the restaurant and branch id.
     * @param restId - restaurant document id field
     * @param branchId - branch document id field
     * @param callBack - A callback method, returns the required object to the caller after
     *                   the object comes back from the Firestore Database.
     */
    public void getMenu(String restId, String branchId, OnDataReceivedFromDB callBack);

    /**
     * Get a `Menu` object using the restaurant and branch id.
     * @param menuPath - A given menu path.
     *                 For example: <collectionName>/<restId>/menus/CSGarr0tfBlOfOUGAga1
     * @param callBack - A callback method, returns the required object to the caller after
     *                   the object comes back from the Firestore Database.
     */
    public void getMenu(String menuPath, OnDataReceivedFromDB callBack);

    /**
     * Get a List<Restaurant> of all restaurants from the database.
     *
     * @param callBack - A callback method, returns the required object to the caller after
     *                   the object comes back from the Firestore Database.
     */
    public void getRestaurants(OnDataReceivedFromDB callBack);

    // Write operations in our database:
    public void addRestaurant(Restaurant restaurant, OnDataSentToDB callBack);

    // More operations will be added later...

    public void addUserWithType(FirebaseUser user, int userType, OnDataSentToDB callback);
}
