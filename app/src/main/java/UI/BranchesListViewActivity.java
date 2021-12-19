package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.exercise_5.R;

import java.util.List;

import BusinessEntities.Branch;
import DataAccessLayer.OnDataReceived;
import DataAccessLayer.RemoteRestDB;
import UIAdapters.BranchAdapter;

public class BranchesListViewActivity extends AppCompatActivity {

    private RemoteRestDB rdb;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_scrollview);
        rdb = RemoteRestDB.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        listView = (ListView) findViewById(R.id.branchListView);

        String restName = getIntent().getStringExtra("restName");

        rdb.getBranches(restName,
                new OnDataReceived() {
            @Override
            public void onObjectReceivedFromDB(Object obj) {

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