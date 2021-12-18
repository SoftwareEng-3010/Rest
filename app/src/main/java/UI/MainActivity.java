package UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.exercise_5.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import BusinessEntities.Branch;
import BusinessEntities.Restaurant;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private FirebaseFirestore db;
    private List<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "onCreate()");
        db = FirebaseFirestore.getInstance(); // Database reference
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart()");

//        getRestaurants("McMoshe");

        Intent intent  = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    /**
     * Method for later use
     * @param name
     */
    private void getRestaurants(String name) {
        db.collection("restaurants").whereEqualTo("name", name)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                restaurantList = new ArrayList<>();
                if (task.isSuccessful()) {
                    List<DocumentSnapshot> docs = task.getResult().getDocuments();
                    for (DocumentSnapshot doc : docs) {
                        Restaurant restaurant = doc.toObject(Restaurant.class);

                        if (restaurant == null) continue;
                        restaurantList.add(restaurant);
                    }
                    Log.e(TAG, restaurantList.toString());
                }


            }
        });
    }
}