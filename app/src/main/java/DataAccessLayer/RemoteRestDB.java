package DataAccessLayer;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

import BusinessEntities.Branch;
import BusinessEntities.Restaurant;


// todo: 1. getRestNames()
// todo: 2. getBranchAddresses(restId, branchId)
// todo: 3. getOpenTables(restId, branchId)
// todo: 4. getBranch(restId, branchId)

/**
 * This class will be similar  to RestDB, but without saving data to device.
 * Instead, the class will be used to query the db for necessary data
 */
public class RemoteRestDB {
    private final String TAG = "RemoteRestDB";       // for debugging

    private static RemoteRestDB instance = null;          // private single instance

    private FirebaseFirestore db;                   // db reference
    private CollectionReference restCollection;     // collection reference

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
            synchronized (RestDB.class){
                if(instance == null){
                    instance = new RemoteRestDB();
                }
            }
        }
        return instance;
    }

    public List<String> getRestNames(){
        // the list we will be returning
        ArrayList<String> restNames = new ArrayList<>();

        // document references
        restCollection.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Log.w(TAG, "Listen failed " + error.getMessage());
                }
                else{
                    List<DocumentSnapshot> documentSnapshots = value.getDocuments();
                    for(DocumentSnapshot documentSnapshot : documentSnapshots){

                    }
                }
            }
        });

        return restNames;
    }

    public List<String> getBranchAddresses(int restId, int branchId){
        return null;
    }

    public List<Integer> getOpenTables(int restId, int branchId){
        return null;
    }
    public Branch getBranch(int restId, int branchId){
        return null;
    }

    public List<Restaurant> getRestaurants(){
        return null;
    }

}
