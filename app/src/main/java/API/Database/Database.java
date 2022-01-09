package API.Database;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;

import API.Controllers.IOrderManager;
import API.Models.IOrder;
import BusinessEntities.Branch;
import BusinessEntities.Menu;
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
    public void getBranches(String restId, DatabaseRequestCallback callBack);

    /**
     * Get a `Branch` object from the database.
     *
     * @param branchId - The document id of the required branch
     *
     * @param callBack - A callback method, returns the required object to the caller after
     *                 the object comes back from the Firestore Database
     */
//    public void getBranch(@NonNull String branchId, DatabaseRequestCallback callBack);

    /**
     * Get a `Branch` object from the database.
     *
     * @param restId - Restaurant document id.
     *
     * @param branchId - Corresponding document id of the required branch
     *
     * @param callBack - A callback method, returns the required object to the caller after
     *                 the object comes back from the Firestore Database
     */
    public void getBranch(@NonNull String restId, @NonNull String branchId, DatabaseRequestCallback callBack);

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
    public void getMenu(String restId, String branchId, String menuPath, DatabaseRequestCallback callBack);

    /**
     * Get a `Menu` object using the restaurant and branch id.
     * @param restId - restaurant document id field
     * @param branchId - branch document id field
     * @param callBack - A callback method, returns the required object to the caller after
     *                   the object comes back from the Firestore Database.
     */
    public void getMenu(String restId, String branchId, DatabaseRequestCallback callBack);

    /**
     * Get a `Menu` object using the restaurant and branch id.
     * @param menuPath - A given menu path.
     *                 For example: <collectionName>/<restId>/menus/CSGarr0tfBlOfOUGAga1
     * @param callBack - A callback method, returns the required object to the caller after
     *                   the object comes back from the Firestore Database.
     */
    public void getMenu(String menuPath, DatabaseRequestCallback callBack);

    /**
     * Get a List<Restaurant> of all restaurants from the database.
     *
     * @param callBack - A callback method, returns the required object to the caller after
     *                   the object comes back from the Firestore Database.
     */
    public void getRestaurants(DatabaseRequestCallback callBack);

    public void getRestaurant(@NonNull String restId, DatabaseRequestCallback callBack);

    public void getUser(String uid, DatabaseRequestCallback callback);

    // Write operations in our database:
    public void addRestaurant(@NonNull Restaurant restaurant, OnDataSentToDB writeCallback, DatabaseRequestCallback requestCallback);

    public void setRestaurant(@NonNull String restId, @NonNull Restaurant restaurant, OnDataSentToDB writeCallback);

    public void addBranch(@NonNull String restId, @NonNull Branch branch, OnDataSentToDB writeCallback, DatabaseRequestCallback requestCallback);

    // More operations will be added later...

    public void addUserWithType(FirebaseUser user, int userType, OnDataSentToDB callback);

    public void setUser(@NonNull Object user, int userType, OnDataSentToDB callback);

    public void addMenu(@NonNull String restId, @NonNull Menu menu, DatabaseRequestCallback callback);

    public void sendOrder(@NonNull String restId, @NonNull String branchId, @NonNull IOrder order, OnDataSentToDB callback);

    public void getOrder(String orderId, DatabaseRequestCallback callback);

    public void attachOrderListener(@NonNull String restId, @NonNull String branchId, IOrderManager listener);
}
