package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.exercise_5.R;

import java.util.List;

import BusinessEntities.Branch;
import BusinessEntities.Restaurant;
import DataAccessLayer.RestDB;

public class BranchesViewActivity extends AppCompatActivity {

    private RestDB rdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.branch_view_activity);
        rdb = RestDB.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        ListView listView = (ListView) findViewById(R.id.branchListView);

        int index = getIntent().getIntExtra("restInd", 0);
        Restaurant selectedRestaurant = rdb.getRestaurants().get(index);

        ArrayAdapter<Branch> adapter = new BranchAdapter(
                this,
                R.layout.item_branch,
                selectedRestaurant.getBranches()
        );

        listView.setAdapter(adapter);
    }

}