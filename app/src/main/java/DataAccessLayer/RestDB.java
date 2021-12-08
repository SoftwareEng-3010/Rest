package DataAccessLayer;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import BusinessLogic.Item;
import BusinessLogic.Restaurant;

public class RestDB {

    private FirebaseFirestore db;
    private CollectionReference restCollection;

    public RestDB() {
        db = FirebaseFirestore.getInstance();
        restCollection = db.collection("restaurants");
    }

    public void getRestaurants(List<Restaurant> restaurants){
        restCollection.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : list){
                                Restaurant rest = d.toObject(Restaurant.class);
                            }
                        }
                    }
                });

    }
}

