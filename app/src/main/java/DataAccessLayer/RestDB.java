package DataAccessLayer;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class RestDB {

    private FirebaseFirestore db;
    private CollectionReference restCollection;

    public RestDB() {
        db = FirebaseFirestore.getInstance();
        restCollection = db.collection("restaurants");
    }

    public Query getRestWithID(int id){
        Query q = restCollection.whereEqualTo("id", id);
        return q;
    }

}