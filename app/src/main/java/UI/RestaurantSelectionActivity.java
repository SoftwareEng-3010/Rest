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

/**
 * This Activity allows the user to select between two
 * options:
 * 1. Scan a QRCode to start a restaurant session
 * 2. Manually select from Restaurants list.
 */
public class RestaurantSelectionActivity extends AppCompatActivity {
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

        qrBtn = findViewById(R.id.qr_button);
        listBtn = findViewById(R.id.list_button);

        initListeners();

        restDB = RestDB.getInstance();
        restaurants = restDB.getRestaurants();
        for (Restaurant r : restaurants) {
            Log.d(TAG, r.toString());
        }
    }

    private void initListeners(){

        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewRestIntent = new Intent(RestaurantSelectionActivity.this, RestaurantsViewActivity.class);
                startActivity(viewRestIntent);
            }
        });

        qrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToQRScannerActivity = new Intent(RestaurantSelectionActivity.this, QRCodeActivity.class);
                startActivity(moveToQRScannerActivity);
            }
        });
    }
}
