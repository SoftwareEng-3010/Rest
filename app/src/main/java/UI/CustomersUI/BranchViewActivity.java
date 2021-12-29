package UI.CustomersUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.exercise_5.R;

import javax.annotation.Nullable;

import BusinessEntities.Branch;
import BusinessEntities.Item;
import BusinessEntities.Menu;
import BusinessEntities.QRCode;
import API.Database.Database;
import API.Database.DatabaseRequestCallback;
import DataAccessLayer.RestDB;
import UIAdapters.MenuRecyclerViewAdapter;
import ViewModels.MenuViewModel;

public class BranchViewActivity extends AppCompatActivity {

    private final String TAG = "BranchViewActivity";

    private Database rdb;

    private TextView branchNameTV;
    private TextView branchBusinessHrsTV;
    private TextView selectedTableTV;

    private RecyclerView menuRecyclerView;

    private MenuRecyclerViewAdapter menuAdapter;
    
    private Branch branch;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_view);
        rdb = RestDB.getInstance();

        // Required data to receive a branch from Database
        String restId = getIntent().getStringExtra(QRCode.KEY_RESTAURANT_ID);
        String branchId = getIntent().getStringExtra(QRCode.KEY_BRANCH_ID);

        // Must come from QRCodeActivity:
        int tableNumber = getIntent().getIntExtra(QRCode.KEY_TABLE_NUMBER, -1);
        // Must come only from manual restaurant selection:
        String menuPath = getIntent().getStringExtra("menu_path");

        // Get Branch from Database
        getBranchAndMenu(restId, branchId, menuPath);


    }

    private void setupUI() {
        branchNameTV = (TextView) findViewById(R.id.branch_name_TV);
        branchBusinessHrsTV = (TextView) findViewById(R.id.branch_business_hrs_TV);
        selectedTableTV = (TextView) findViewById(R.id.user_selected_table_TV);

        // Menu Recycler View ref
        menuRecyclerView = (RecyclerView) findViewById(R.id.branch_menu_recycle_view);

        // Initialize ViewModel
        MenuViewModel menuViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication())
                .create(MenuViewModel.class);

        // set up adapter
        menuAdapter = new MenuRecyclerViewAdapter(BranchViewActivity.this, menu.getMenu());

        // set up the RecyclerView
        menuRecyclerView = (RecyclerView) findViewById(R.id.branch_menu_recycle_view);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(BranchViewActivity.this));
        menuRecyclerView.setAdapter(menuAdapter);

        Button buttonSubmit = (Button) findViewById(R.id.button_submit_order);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Item i : menuAdapter.getSelectedItems()) {
                    Log.d(TAG, i.getName());
                }
            }
        });
    }

    public void getBranchAndMenu(String restId, String branchId, String menuPath) {

        rdb.getBranch(branchId, new DatabaseRequestCallback() {
            @Override
            public void onObjectReturnedFromDB(@Nullable Object obj) {
                branch = (Branch) obj;
                if (branch != null) {
                    rdb.getMenu(restId, branchId, menuPath,
                            new DatabaseRequestCallback() {
                        @Override
                        public void onObjectReturnedFromDB(@Nullable Object obj) {
                            menu = (Menu) obj;
                            setupUI();
                        }
                    });
                }
                else {
                    Log.e(TAG, "An error occurred retrieving Branch "
                            + branchId + " from Database");
                }
            }
        });
    }
}