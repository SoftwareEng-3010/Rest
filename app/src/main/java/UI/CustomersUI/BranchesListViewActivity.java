package UI.CustomersUI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.exercise_5.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import API.Constants.Constants;
import API.Database.DatabaseRequestCallback;
import BusinessEntities.Branch;
import BusinessEntities.QRCode;
import BusinessEntities.Table;
import DataAccessLayer.RestDB;
import UIAdapters.BranchArrayAdapter;

public class BranchesListViewActivity extends AppCompatActivity{

    private RestDB rdb;
    private ListView listView;
    private List<Branch> branches;

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
        String restID = getIntent().getStringExtra("rest_id");


        // Retrieve data from Database
        rdb.getBranches(restID,
                new DatabaseRequestCallback() {
                    @Override
                    public void onObjectReturnedFromDB(Object obj) {

                        // When data arrives from DB - init the ListView adapter
                        branches = (List<Branch>) obj;
                        ArrayAdapter<Branch> adapter = new BranchArrayAdapter(
                                BranchesListViewActivity.this,
                                R.layout.layout_branch_item,
                                branches,
                                restID
                        );
                        listView.setAdapter(adapter);
                    }
                });
    }
}