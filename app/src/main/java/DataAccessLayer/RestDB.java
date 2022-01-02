package DataAccessLayer;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import API.Constants.Constants;
import API.Database.Database;
import API.Database.DatabaseRequestCallback;
import API.Database.OnDataSentToDB;
import API.IOrderController;
import API.Models.IBranchManagerUser;
import API.Models.IOrder;
import API.Models.IUser;
import BusinessEntities.Branch;
import BusinessEntities.BranchManager;
import BusinessEntities.Customer;
import BusinessEntities.Menu;
import BusinessEntities.Restaurant;
import BusinessEntities.User;

/**
 * This class implements the Database interface and affectively performs all CRUD operations
 * on our Firestore database
 */
public class RestDB implements Database {

    private final String TAG = "RestDB";

    // constant strings for querying database
    private final String BRANCHES_COLLECTION_NAME = "branch";
    private final String RESTAURANT_COLLECTION_NAME = "our_restaurants";
    private final String MENU_FIELD_NAME = "menu_path";
    private final String MENU_COLLECTION_NAME = "menu";

    private static RestDB instance = null;                    // private single instance

    private final FirebaseFirestore db;                       // Database reference
    private final CollectionReference restCollection;         // Restaurants collection reference

    private RestDB() {
        // Database and collection references
        db = FirebaseFirestore.getInstance();
        restCollection = db.collection(RESTAURANT_COLLECTION_NAME);
    }

    /**
     * Thread safe getInstance method that returns the single static instance of this class
     *
     * @return RestDB single static instance
     */
    public static RestDB getInstance() {
        if (instance == null) {
            synchronized (RestDB.class) {
                if (instance == null) {
                    instance = new RestDB();
                }
            }
        }
        return instance;
    }

    /*
    Firestore database Querying methods:
     */
    @Override
    public void getBranches(String restId, DatabaseRequestCallback callBack) {

        List<Branch> branches = new ArrayList<>();

        restCollection.document(restId).collection(BRANCHES_COLLECTION_NAME).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                                branches.add(queryDocumentSnapshot.toObject(Branch.class));
                            }
                            callBack.onObjectReturnedFromDB(branches);
                        } else if (null != task.getException()){
                            callBack.onObjectReturnedFromDB(null);
                            Log.e(TAG, task.getException().getMessage());
                        }
                    }
                });
    }


    @Override
    public void getBranch(@NonNull String restId, @NonNull String branchId, DatabaseRequestCallback callBack) {

        restCollection.document(restId)
                .collection(BRANCHES_COLLECTION_NAME)
                .document(branchId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {

                            callBack.onObjectReturnedFromDB(task.getResult().toObject(Branch.class));
                        }

                        else {
                            // Task is not successful
                            callBack.onObjectReturnedFromDB(null);
                        }
                    }
                });
    }

    @Override
    public void getMenu(String restId, String branchId, String menuPath, DatabaseRequestCallback callBack) {
        if (restId != null && branchId != null) {
            // menuPath will be null if the user scans a QRCode
            getMenu(restId, branchId, callBack);
        }
        else if (menuPath != null) {
            // restId will be null if the user uses manual restaurant search
            getMenu(menuPath, callBack);
        }
        else {
            // All fields passed to the method are null...

            callBack.onObjectReturnedFromDB(null);
            Log.e(TAG, "restId, branchId and menuPath are null!!!");
        }
    }

    @Override
    public void getMenu(String restId, String branchId, DatabaseRequestCallback callBack) {

        CollectionReference branchCollection =
                restCollection.document(restId).collection(BRANCHES_COLLECTION_NAME);

        DocumentReference branchDocRef = branchCollection.document(branchId);

        branchDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot documentSnapshot = task.getResult();
                    String menuPath = (String) documentSnapshot.get(MENU_FIELD_NAME);

                    if (null == menuPath) Log.e(TAG, "Failed to retrieve menu path from database. menu_path is null");

                    DocumentReference menuDocRef = db.document(menuPath);

                    menuDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot menuSnapshot = task.getResult();
                                Menu menu = menuSnapshot.toObject(Menu.class);
                                callBack.onObjectReturnedFromDB(menu);
                            }

                            else if (null != task.getException()){
                                // An error occurred
                                Log.e(TAG, task.getException().getMessage());
                                callBack.onObjectReturnedFromDB(null);
                            }
                        }
                    });
                }

                else if (null != task.getException()){
                    // An error occurred
                    Log.e(TAG, task.getException().getMessage());
                }
            }
        });
    }

    @Override
    public void getMenu(String menuPath, DatabaseRequestCallback callBack) {
        db.document(menuPath).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot menuSnapshot = task.getResult();
                            callBack.onObjectReturnedFromDB(menuSnapshot.toObject(Menu.class));
                        }

                        else if (null != task.getException()){
                            // An error occurred
                            Log.e(TAG, task.getException().getMessage());
                            callBack.onObjectReturnedFromDB(null);
                        }
                    }
                });
    }

    @Override
    public void getRestaurants(DatabaseRequestCallback callBack) {

        List<Restaurant> restaurants = new ArrayList<>();

        restCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshots : task.getResult()) {
                        restaurants.add(documentSnapshots.toObject(Restaurant.class));
                    }
                    callBack.onObjectReturnedFromDB(restaurants);
                }

                else if (null != task.getException()){
                    // An error occurred
                    Log.e(TAG, task.getException().getMessage());
                    callBack.onObjectReturnedFromDB(null);
                }
            }
        });
    }

    /*
        Firestore database Writing methods:
    */
    @Override
    public void addRestaurant(Restaurant restaurant, OnDataSentToDB callBack) {
        // Implement

        CollectionReference test_collection = db.collection("test");

        test_collection.document() // A new document reference
                        .set(restaurant)
                        .addOnCompleteListener(
                                new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.e(TAG, "Successfully written object to database!");
                                            callBack.onObjectWrittenToDB(true);
                                        }
                                        else {
                                            Log.e(TAG, "Something went wrong while writing an object to database");
                                            callBack.onObjectWrittenToDB(false);
                                        }
                                    }
                                }
                        );
        Log.e(TAG, "finished addRestaurant()");

    }

    @Override
    public void addUserWithType(FirebaseUser user, int userType, OnDataSentToDB callback) {

        IUser newUser = new IUser() {
            @Override
            public String getUid() {
                return user.getUid();
            }

            @Override
            public int getType() {
                return userType;
            }
        };

        db.collection("users")
                .document(user.getUid())
                .set(newUser)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.e(TAG, "Customer was successfully written to database");
                            callback.onObjectWrittenToDB(true);
                        }
                        else {
                            Log.e(TAG, "Customer was successfully written to database");
                            callback.onObjectWrittenToDB(false);
                        }
                    }
                }
        );
    }

    @Override
    public void addMenu(@NonNull String restId, @NonNull Menu menu, OnDataSentToDB callback) {

        restCollection
                .document(restId)
                .collection(MENU_COLLECTION_NAME)
                .add(menu.getMenuItems())
                .addOnCompleteListener(
                        new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if (task.isSuccessful()) {

                                    callback.onObjectWrittenToDB(true);
                                }
                                else {
                                    callback.onObjectWrittenToDB(false);
                                }
                            }
                        }
                );
    }

    @Override
    public void getUser(String uid, DatabaseRequestCallback callback) {

        db.collection("users")
                .document(uid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            // If uid is found as:

                           User user = task.getResult().toObject(User.class);

                           if (null == user) {callback.onObjectReturnedFromDB(null); return;}

                           if (user.getType() == Constants.USER_TYPE_CUSTOMER) {
                               callback.onObjectReturnedFromDB(task.getResult().toObject(Customer.class));
                           }

                           else if (user.getType() == Constants.USER_TYPE_BRANCH_MANAGER) {
                               IBranchManagerUser manager = task.getResult().toObject(BranchManager.class);
                               callback.onObjectReturnedFromDB(manager);
                           }

                           else if (user.getType() > Constants.USER_TYPE_BRANCH_MANAGER) {
                               Log.e(TAG, "Some kind of service unit type of user is signed in with user_type = " + user.getType());
                               callback.onObjectReturnedFromDB(task.getResult().toObject(User.class));
                           }

                           else {
                               Log.e(TAG, "Could not find a class for the user returned from db");
                               callback.onObjectReturnedFromDB(null);
                           }
                        }
                    }
                });
    }

    @Override
    public void sendOrder(@NonNull String restId,@NonNull String branchId,@NonNull IOrder order, OnDataSentToDB callback) {
        Log.e(TAG, "IMPLEMENT pushOrder");

        restCollection.document(restId)
                .collection(BRANCHES_COLLECTION_NAME)
                .document(branchId)
                .collection("orders")
                .document()
                .set(order)
                .addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            callback.onObjectWrittenToDB(true);
                            Log.e(TAG, "New Order is waiting to be handled by branch: " + branchId);
                        }
                        else {
                            callback.onObjectWrittenToDB(false);
                        }
                     }
                }
        );

    }

    @Override
    public void getOrder(String orderId, DatabaseRequestCallback callback) {
        Log.e(TAG, "IMPLEMENT pullOrder");
    }

    @Override
    public void attachOrderListener(@NonNull String restId, @NonNull String branchId, IOrderController listener) {

        restCollection.document(restId)
                .collection(BRANCHES_COLLECTION_NAME)
                .document(branchId)
                .collection("orders")
                .addSnapshotListener(listener);
    }

}