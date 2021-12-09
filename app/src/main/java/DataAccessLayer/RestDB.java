package DataAccessLayer;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.widget.Toast;

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
        Log.d("getRestaurants", "Start");
        restCollection.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                //Restaurant rest = d.toObject(Restaurant.class);
                                Log.d("Rest: ", d.toString());
                            }
                        }
                        else{
                            Log.d("getRestaurants", "empty query");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("getRestaurants", "task failed");
                    }
                });
    }
}

