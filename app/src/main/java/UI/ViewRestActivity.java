package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exercise_5.R;

import java.util.ArrayList;
import java.util.List;

import BusinessEntities.Restaurant;
import DataAccessLayer.RestDB;

public class ViewRestActivity extends AppCompatActivity {

    private RestDB rdb;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rest);
        rdb = RestDB.getInstance();

        scrollView = (ScrollView)findViewById(R.id.restScrollView);

        TextView text = new TextView(this);

        text.setText("Hello world! 1");

        scrollView.addView(text);
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<Restaurant> restaurant = rdb.getRestaurants();
    }
}