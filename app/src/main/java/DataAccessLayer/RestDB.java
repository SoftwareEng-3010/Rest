package DataAccessLayer;

import androidx.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;


import BusinessEntities.Restaurant;

public class RestDB {

    FileWriter docs;
    FileWriter names;

    private FirebaseFirestore db;
    private CollectionReference restCollection;

    // Constructor
    public RestDB() {

        db = FirebaseFirestore.getInstance();
        restCollection = db.collection("restaurants");
    }

    // temp method for testing querying on Firestore
    public void getRestaurants(List<Restaurant> restaurants){
        Log.d("getRestaurants", "Start");
        restCollection.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                Log.d("getRestaurants", d.toString());
                                Restaurant rest = d.toObject(Restaurant.class);
                                Log.d("getRestaurants", rest.getName());
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

