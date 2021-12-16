package DataAccessLayer;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import BusinessEntities.Branch;


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

    private static RestDB instance = null;          // private single instance

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

    public List<String> getRestNames(){
        return null;
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

}
