package DataAccessLayer;

import androidx.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;


import BusinessEntities.Restaurant;

public class RestDB {

    private final String TAG = "RestDB";

    private FirebaseFirestore db;                   // db reference
    private CollectionReference restCollection;     // collection reference

    // Constructor
    public RestDB() {
        db = FirebaseFirestore.getInstance();
        restCollection = db.collection("restaurants");
    }

    // temp method for testing querying on Firestore
    public void getRestaurants(List<Restaurant> restaurants){
        Log.d(TAG, "getRestaurants start");

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
                    Log.d(TAG, task.getException().toString());
                }
            }
        });
//        restCollection.get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
//                        if (!queryDocumentSnapshots.isEmpty()) {
//                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//                            for (DocumentSnapshot d : list) {
//                                Restaurant rest = d.toObject(Restaurant.class);
//                                restaurants.add(rest);
//                            }
//                        }
//                        else{
//                            Log.d(TAG, "empty query");
//                        }
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d("getRestaurants", "task failed");
//                    }
//                });
    }
}

