package DataAccessLayer;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import BusinessEntities.Address;
import BusinessEntities.Branch;
import BusinessEntities.Item;
import BusinessEntities.Menu;
import BusinessEntities.Restaurant;

/**
 * This class will be similar  to RestDB, but without saving data to device.
 * Instead, the class will be used to query the db for necessary data
 */
public class RestDB {

    private final String TAG = "RestDB";                // for debugging

    // constant strings for querying database
    private final String BRANCHES_COLLECTION_NAME =  "branch";
    private final String RESTAURANT_COLLECTION_NAME = "our_restaurants";
    private final String MENU_COLLECTION_NAME = "menus";
    private final String MENU_FIELD_NAME = "menu";

    private static RestDB instance = null;              // private single instance

    private FirebaseFirestore db;                       // db reference
    private CollectionReference restCollection;         // collection reference

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


    public void getBranches(String restId, OnDataReceived dataClient){

        List<Branch> branches = new ArrayList<>();

        restCollection.document(restId).collection(BRANCHES_COLLECTION_NAME).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                                branches.add(queryDocumentSnapshot.toObject(Branch.class));
                            }
                            dataClient.onObjectReturnedFromDB(branches);
                        } else {
                            Log.e(TAG, task.getException().getMessage());
                        }
                    }
                });

    }

    public void getBranch(String restId, String branchId, OnDataReceived dataClient){
        CollectionReference branchCollection =
                restCollection.document(restId).collection(BRANCHES_COLLECTION_NAME);

        DocumentReference branchDocRef = branchCollection.document(branchId);

        branchDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    dataClient.onObjectReturnedFromDB(documentSnapshot.toObject(Branch.class));
                } else {
                    Log.e(TAG, task.getException().getMessage());
                }
            }
        });
    }

    public void getMenu(String menuPath, OnDataReceived dataClient){
        db.document(menuPath).get()
            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        DocumentSnapshot menuSnapshot = task.getResult();
                        dataClient.onObjectReturnedFromDB(menuSnapshot.toObject(Menu.class));
                    } else {
                        Log.e(TAG, task.getException().getMessage());
                    }
                }
            });
    }


    public void getMenu(String restId, String branchId, OnDataReceived dataClient){

        CollectionReference branchCollection =
                restCollection.document(restId).collection(BRANCHES_COLLECTION_NAME);

        CollectionReference menuCollection =
                restCollection.document(restId).collection(MENU_COLLECTION_NAME);

        DocumentReference branchDocRef = branchCollection.document(branchId);

        branchDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){

                    DocumentSnapshot documentSnapshot = task.getResult();
                    String menuId = (String)documentSnapshot.get(MENU_FIELD_NAME);
                    DocumentReference menuDocRef = menuCollection.document(menuId);

                    menuDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()){
                                DocumentSnapshot menuSnapshot = task.getResult();
                                dataClient.onObjectReturnedFromDB(menuSnapshot.toObject(Menu.class));
                            } else {
                                Log.e(TAG, task.getException().getMessage());
                            }
                        }
                    });
                } else {
                    Log.e(TAG, task.getException().getMessage());
                }
            }
        });
    }

    public void getRestaurants(OnDataReceived dataReceived){

        List<Restaurant> restaurants = new ArrayList<>();

        restCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot documentSnapshots : task.getResult()){
                        restaurants.add(documentSnapshots.toObject(Restaurant.class));
                    }
                    dataReceived.onObjectReturnedFromDB(restaurants);
                } else {
                    Log.e(TAG, task.getException().getMessage());
                }
            }
        });
    }

}


