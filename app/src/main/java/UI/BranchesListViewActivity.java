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
import UIAdapters.BranchAdapter;

public class BranchesListViewActivity extends AppCompatActivity {

    private RestDB rdb;
    private ListView listView;

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
        String restName = getIntent().getStringExtra("restName");

        // Retrieve data from Database
        rdb.getBranches(restName,
                new OnDataReceived() {
                    @Override
                    public void onObjectReturnedFromDB(Object obj) {

                        // When data arrives from DB - init the ListView adapter
                        List<Branch> branches = (List<Branch>) obj;
                        ArrayAdapter<Branch> adapter = new BranchAdapter(
                                getApplicationContext(),
                                R.layout.item_branch,
                                branches
                        );

                        listView.setAdapter(adapter);
                    }
                });
    }

}