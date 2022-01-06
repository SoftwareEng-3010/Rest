package UI.CustomersUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.exercise_5.R;

import java.util.List;

import API.Constants.Constants;
import API.Database.DatabaseRequestCallback;
import BusinessEntities.Branch;
import DataAccessLayer.RestDB;
import UIAdapters.BranchArrayAdapter;

public class BranchesListViewActivity extends AppCompatActivity implements IActionListenerBranch {

    private RestDB rdb;
    private ListView listView;
    private List<Branch> branches;
    private String restID;

    private final String TAG = "BranchListViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_scrollview);
        rdb = RestDB.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Listview reference
        listView = (ListView) findViewById(R.id.branchListView);

        // Get a list of branches and set up the listview adapter
        setListViewAdapter();
    }

    private void setListViewAdapter() {
        // Extract restID from previous activity
        restID = getIntent().getStringExtra("rest_id");


        // Retrieve data from Database
        rdb.getBranches(restID,
                new DatabaseRequestCallback() {
                    @Override
                    public void onObjectReturnedFromDB(Object obj) {

                        // When data arrives from DB - init the ListView adapter
                        branches = (List<Branch>) obj;
                        ArrayAdapter<Branch> adapter = new BranchArrayAdapter(
                                BranchesListViewActivity.this,
                                BranchesListViewActivity.this,
                                R.layout.layout_branch_item,
                                branches,
                                restID
                        );
                        listView.setAdapter(adapter);
                    }
                });
    }

    @Override
    public void onSpecialAction(Branch branch, int tableNumber) {

        Intent moveToBranchViewActivity =
                new Intent(this, BranchView.class);

        String branchId = branch.getDocId();

        moveToBranchViewActivity.putExtra(Constants.KEY_RESTAURANT_ID, restID);
        moveToBranchViewActivity.putExtra(Constants.KEY_BRANCH_ID, branchId);
        moveToBranchViewActivity.putExtra(Constants.KEY_TABLE_NUMBER, tableNumber);
        startActivity(moveToBranchViewActivity);
        finish();
    }
}