package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.exercise_5.R;

import BusinessEntities.Branch;
import BusinessEntities.Restaurant;
import DataAccessLayer.RemoteRestDB;
import DataAccessLayer.RestDB;
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

        int index = getIntent().getIntExtra("restInd", 0);

        ArrayAdapter<Branch> adapter = new BranchAdapter(
                this,
                R.layout.item_branch,

        );

        listView.setAdapter(adapter);
    }

}