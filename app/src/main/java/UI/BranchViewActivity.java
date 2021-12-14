package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.exercise_5.R;

import BusinessEntities.Branch;
import BusinessEntities.Item;
import BusinessEntities.Restaurant;
import DataAccessLayer.RestDB;

public class BranchViewActivity extends AppCompatActivity {

    private final String SELECTED_RESTAURANT_INDEX  = "restaurant_index";
    private final String SELECTED_BRANCH_INDEX = "branch_index";
    private final String SELECTED_TABLE_INDEX = "table_index";

    private final String TAG = "BranchViewActivity";

    private RestDB restDB;

    private TextView branchNameText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_display);

        restDB = RestDB.getInstance();

        int selectedRestaurant = getIntent().getIntExtra(SELECTED_RESTAURANT_INDEX, -1);
        int selectedBranch = getIntent().getIntExtra(SELECTED_BRANCH_INDEX, -1);
        int selectedTable = getIntent().getIntExtra(SELECTED_TABLE_INDEX, -1);

        Restaurant restaurant = RestDB.getInstance().getRestaurants().get(selectedRestaurant);
        Branch branch = restaurant.getBranches().get(selectedBranch);

        branchNameText = findViewById(R.id.branchNameDisplayTextView);
        branchNameText.setText(restaurant.getBranches().get(selectedBranch).getAddress().get("city"));

        for (Item item : branch.getMenu())
        Log.d(TAG, item.toString());
    }
}