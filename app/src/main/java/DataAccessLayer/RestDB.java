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
import java.util.List;

import BusinessEntities.Address;
import BusinessEntities.Branch;
import BusinessEntities.Restaurant;

/**
 * This class will be similar  to RestDB, but without saving data to device.
 * Instead, the class will be used to query the db for necessary data
 */
public class RestDB {

    private final String TAG = "RestDB";                // for debugging
    private final String BRANCHES_COLLECTION_NAME =  "branches";
    private final String RESTAURANT_COLLECTION_NAME = "test";

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


    public void getBranches(String restId, OnDataReceived dataClient) {
        CollectionReference branchesCollection =
                restCollection.document(restId).collection(BRANCHES_COLLECTION_NAME);

        ArrayList<Branch> branches = new ArrayList<>();

        branchesCollection.addSnapshotListener( new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.w(TAG, "Listen failed " + error.getMessage());
                } else if (value != null) {
                    List<DocumentSnapshot> documentSnapshots = value.getDocuments();
                    for (DocumentSnapshot documentSnapshot : documentSnapshots) {
                        branches.add(documentSnapshot.toObject(Branch.class));
                    }
                    dataClient.onObjectReturnedFromDB(branches);
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
                }
                else{
                    Log.w(TAG, "Query Failed");
                }
            }
        });
    }


}


