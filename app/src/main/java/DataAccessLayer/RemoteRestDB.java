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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import BusinessEntities.Address;
import BusinessEntities.Branch;
import BusinessEntities.Restaurant;


// todo: 1. getRestNames() - v
// todo: 2. getBranchAddresses(restId, branchId)
// todo: 3. getOpenTables(restId, branchId)
// todo: 4. getBranch(restId, branchId)

/**
 * This class will be similar  to RestDB, but without saving data to device.
 * Instead, the class will be used to query the db for necessary data
 */
public class RemoteRestDB {
    private final String TAG = "RemoteRestDB";          // for debugging

private static RemoteRestDB instance = null;            // private single instance

    private FirebaseFirestore db;                       // db reference
    private CollectionReference restCollection;         // collection reference

    private List <Branch> branches;

    private RemoteRestDB(){
        // Database and collection references
        db = FirebaseFirestore.getInstance();
        restCollection = db.collection("restaurants");
    }

    /**
     * Thread safe getInstance method that returns the single static instance of this class
     * @return RestDB single static instance
     */
    public static RemoteRestDB getInstance(){
        if(instance == null){
            synchronized (RemoteRestDB.class){
                if(instance == null){
                    instance = new RemoteRestDB();
                }
            }
        }
        return instance;
    }

    public void writeRestaurantToDB(Restaurant restaurant) {

        DocumentReference newRestaurantDoc = restCollection.document();
//        String docId = newRestaurantDoc.getId();

        newRestaurantDoc.set(restaurant).addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.e(TAG, "Restaurant successfully written to Database");
                        }
                        else {
                            Log.e(TAG, "Failed to write restaurant to Database");
                        }
                    }
                }
        );
    }


    public List<Restaurant> getRestaurantDoNotUse(String name) {

        List<Restaurant> restaurantList = new ArrayList<>();
        restCollection.whereEqualTo("name", name).get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                restaurantList.add(doc.toObject(Restaurant.class));
                            }
                        }
                    }
                }
        );
        return restaurantList;
    }



    public List<Branch> getBranchDoNotUse(String name, int id) {

        List<Branch> queriedBranches = new ArrayList<>();
        restCollection.whereEqualTo("name", name).whereEqualTo("id", id).get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                queriedBranches.add(doc.toObject(Branch.class));
                            }
                        }
                    }
                }
        );
        return queriedBranches;
    }

    /**
     * Gets the names of all the restaurants in our collection
     * @return the names of all the restaurants
     */
    public List<String> getRestaurant(){

        // the list we will be returning
        ArrayList<String> restNames = new ArrayList<>();

        // document references
        restCollection.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Log.w(TAG, "Listen failed " + error.getMessage());
                }
                else if (value != null) {
                    List<DocumentSnapshot> documentSnapshots = value.getDocuments();

                    for(DocumentSnapshot documentSnapshot : documentSnapshots){
                        String restName = (String) documentSnapshot.get("name");
                        restNames.add(restName);
                    }
                }
            }
        });
        return restNames;
    }

    public List<String> getBranchAddresses(int restId){
        return null;
    }

    public List<Integer> getOpenTables(int restId, int branchId){
        return null;
    }
    public Branch getBranch(String restId, String branchAddress) {

        Restaurant restaurant = getRestaurant(restId);

        for (Branch b : restaurant.getBranches()) {
            if (b.getAddress().equals(new Address(branchAddress)))
                return b;
        }
        return null;
    }

    public void getBranches(String restName, OnDataReceived dataReceivedCallback) {
        restCollection.whereEqualTo("name", restName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().size() != 1){
                                throw new RuntimeException("RestID provided does not return a single document.");
                            }
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                branches = (List<Branch>) document.get("branches");
                                for (Branch branch : branches) {

                                }
                                dataReceivedCallback.onObjectReceivedFromDB(branches);
                            }
                        }
                    }
                });
    }


    public Restaurant getRestaurant(String id) {

        List<Restaurant> restaurants = new ArrayList<>();

        // document references
        restCollection.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Log.w(TAG, "Listen failed " + error.getMessage());
                }

                else if(value != null){
                    List<DocumentSnapshot> documentSnapshots = value.getDocuments();
                    for(DocumentSnapshot documentSnapshot : documentSnapshots){
                        Restaurant restaurant = documentSnapshot.toObject(Restaurant.class);
                        restaurants.add(restaurant);
                    }
                }

                else {
                    Log.e(TAG, "Query failed");
                }
            }
        });
        return restaurants.get(0);
    }

    /**
     * Gets a list of all the restaurants in our collection
     * @return all the restaurants in our collection
     */
    public List<Restaurant> getRestaurants(){
        // the list we will be returning
        ArrayList<Restaurant> restaurants = new ArrayList<>();

        // document references
        restCollection.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Log.w(TAG, "Listen failed " + error.getMessage());
                }

                else if(value != null){
                    List<DocumentSnapshot> documentSnapshots = value.getDocuments();
                    for(DocumentSnapshot documentSnapshot : documentSnapshots){
                        Restaurant restaurant = documentSnapshot.toObject(Restaurant.class);
                        restaurants.add(restaurant);
                    }
                }

                else {
                    Log.e(TAG, "Query failed");
                }
            }
        });
        return restaurants;
    }

}
