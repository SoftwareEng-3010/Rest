package DataAccessLayer;

import static org.junit.Assert.*;

import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import BusinessEntities.Branch;

public class RestDBTest {

    private static RestDB instance = RestDB.getInstance();             // private single instance

    private FirebaseFirestore db;                       // db reference
    private CollectionReference restCollection;         // collection reference



    @Test
    public void getBranches() {
        instance.getBranches("7E1eWyWB8feUSPiI5WlI", new OnDataReceived() {
            @Override
            public void onObjectReturnedFromDB(Object obj) {
                List<Branch> branches = (List<Branch>) obj;
                Log.d("getBranches Test", branches.toString());
            }
        });
    }

    @Test
    public void getBranch() {
        instance.getBranch("7E1eWyWB8feUSPiI5WlI", "SPFRzmgrYwSM4olRllyH", new OnDataReceived() {
            @Override
            public void onObjectReturnedFromDB(Object obj) {
                Branch branch = (Branch) obj;
                Log.d("getBranch Test", branch.toString());
            }
        });
    }
}