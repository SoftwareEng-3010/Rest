package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.exercise_5.R;

import java.util.List;

import BusinessEntities.Branch;
import DataAccessLayer.OnDataReceived;
import DataAccessLayer.RestDB;
import UIAdapters.BranchArrayAdapter;

public class BranchesListViewActivity extends AppCompatActivity {

    private RestDB rdb;
    private ListView listView;
    private List<Branch> branches;

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
        String restID = getIntent().getStringExtra("restID");


        // Retrieve data from Database
        rdb.getBranches(restID,
                new OnDataReceived() {
                    @Override
                    public void onObjectReturnedFromDB(Object obj) {

                        // When data arrives from DB - init the ListView adapter
                        branches = (List<Branch>) obj;
                        ArrayAdapter<Branch> adapter = new BranchArrayAdapter(
                                BranchesListViewActivity.this,
                                R.layout.layout_branch_item,
                                branches
                        );
                        listView.setAdapter(adapter);
                    }
                });
    }

}