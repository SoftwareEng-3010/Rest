package DataAccessLayer;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;


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

    }

}
