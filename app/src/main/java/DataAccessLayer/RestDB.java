package DataAccessLayer;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import API.Database.Database;
import API.Database.OnDataReceivedFromDB;
import API.Database.OnDataSentToDB;
import BusinessEntities.Branch;
import BusinessEntities.Menu;
import BusinessEntities.Restaurant;

/**
 * This class will be similar  to RestDB, but without saving data to device.
 * Instead, the class will be used to query the db for necessary data
 */
public class RestDB implements Database {

    private final String TAG = "RestDB";

    // constant strings for querying database
    private final String BRANCHES_COLLECTION_NAME = "branch";
    private final String RESTAURANT_COLLECTION_NAME = "our_restaurants";
    private final String MENU_FIELD_NAME = "menu_path";

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
    public void getBranches(String restId, OnDataReceivedFromDB callBack) {

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
    public void getBranch(String branchId, OnDataReceivedFromDB callBack) {

        restCollection.get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> restaurantDocs = task.getResult().getDocuments();
                            for (DocumentSnapshot doc : restaurantDocs) {
                                restCollection.document(doc.getId())
                                        .collection(BRANCHES_COLLECTION_NAME).get()
                                        .addOnCompleteListener(
                                                new OnCompleteListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                                        if (task.isSuccessful()) {
                                                            List<DocumentSnapshot> branchDocs = task.getResult().getDocuments();

                                                            for (DocumentSnapshot doc : branchDocs) {
                                                                if (branchId.equals(doc.getId())) {
                                                                    callBack.onObjectReturnedFromDB(
                                                                            doc.toObject(Branch.class)
                                                                    );
                                                                }
                                                            }
                                                            callBack.onObjectReturnedFromDB(null);
                                                        } else {
                                                            // Task unsuccessful
                                                            callBack.onObjectReturnedFromDB(null);
                                                        }
                                                    }
                                                }
                                        );
                            }
                        } else if (null != task.getException()){
                            callBack.onObjectReturnedFromDB(null);
                            Log.e(TAG, task.getException().getMessage());
                        }
                    }
                }
        );
    }

    @Override
    public void getMenu(String restId, String branchId, String menuPath, OnDataReceivedFromDB callBack) {
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
    public void getMenu(String restId, String branchId, OnDataReceivedFromDB callBack) {

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
    public void getMenu(String menuPath, OnDataReceivedFromDB callBack) {
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
    public void getRestaurants(OnDataReceivedFromDB callBack) {

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
    }
}