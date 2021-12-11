package DataAccessLayer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


import BusinessEntities.Restaurant;

/**
 * A singleton class to handle accessing the restaurants db
 */
public class RestDB {

    private final String TAG = "RestDB";            // for debugging

    private static RestDB instance = null;          // private single instance

    private FirebaseFirestore db;                   // db reference
    private CollectionReference restCollection;     // collection reference

    private List<Restaurant> restaurants;           // list of currently active restaurants

    /**
     * Private constructor
     */
    private RestDB() {

        // databse and collection references
        db = FirebaseFirestore.getInstance();
        restCollection = db.collection("restaurants");

        fetchRestaurants();

        // listening on changes in restaurants collection and updating our list accordingly
        restCollection.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Log.w(TAG, "Listen failed " + error.getMessage());
                }
                else{
                    List<DocumentSnapshot> documentSnapshots = value.getDocuments();
                    updateRestaurants(documentSnapshots);
                }
            }
        });
    }

    /**
     * Thread safe getInstance method that returns the single static instance of this class
     * @return RestDB single static instance
     */
    public static RestDB getInstance(){
        if(instance == null){
            synchronized (RestDB.class){
                if(instance == null){
                    instance = new RestDB();
                }
            }
        }
        return instance;
    }

    private void updateRestaurants(List<DocumentSnapshot> documentSnapshots){
        restaurants.clear();
        for(DocumentSnapshot d : documentSnapshots){
            Restaurant restaurant = d.toObject(Restaurant.class);
            restaurants.add(restaurant);
        }
    }

    private void fetchRestaurants(){
        restaurants = new ArrayList<>();

        restCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            // If we were able to fetch all the documents, convert them to Restaurant object
            // and insert them into the list
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot queryRes = task.getResult();
                    List<DocumentSnapshot> documents = queryRes.getDocuments();
                    for(DocumentSnapshot doc : documents){
                        Restaurant restaurant = doc.toObject(Restaurant.class);
                        Log.d(TAG, restaurant.toString());
                        restaurants.add(restaurant);
                    }
                }
                else{
                    Log.d(TAG, task.getException().getMessage());
                }
            }
        });
    }

    // temp method for testing querying on Firestore
    public List<Restaurant> getRestaurants(){
        return this.restaurants;
    }
}

