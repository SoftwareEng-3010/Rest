package UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.exercise_5.R;

import java.util.List;

import BusinessEntities.Restaurant;
import DataAccessLayer.RestDB;

public class RestSelector extends AppCompatActivity {
    private RestDB restDB;
    private List<Restaurant> restaurants;

    private final String TAG = "RestSelector";

    private Button qrBtn;
    private Button listBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_selector);

        String userEmail = getIntent().getStringExtra("UserEmail");
        Toast.makeText(this, "Welcome, " + userEmail, Toast.LENGTH_SHORT).show();
        String userName = getIntent().getStringExtra("UserEmail");
        Toast.makeText(this, "Welcome, " + userName, Toast.LENGTH_SHORT).show();

        qrBtn = (Button)findViewById(R.id.qr_button);
        listBtn = (Button)findViewById(R.id.list_button);

        restDB = RestDB.getInstance();
        restaurants = restDB.getRestaurants();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, Integer.toString(RestDB.getInstance().getRestaurants().size()));

//        restDB = RestDB.getInstance();
//        restaurants = restDB.getRestaurants();
        for (Restaurant r : RestDB.getInstance().getRestaurants())
            Log.d(TAG, r.toString());
        Log.d(TAG, Integer.toString(restaurants.size()));
        initListeners();
    }

    private void initListeners(){

        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewRestIntent = new Intent(RestSelector.this, ViewRestActivity.class);
                startActivity(viewRestIntent);
            }
        });
    }
}
