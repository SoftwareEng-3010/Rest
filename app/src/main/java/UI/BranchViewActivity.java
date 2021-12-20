package UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exercise_5.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import BusinessEntities.Address;
import BusinessEntities.Branch;
import BusinessEntities.Item;
import BusinessEntities.QRCode;
import BusinessEntities.Restaurant;
import DataAccessLayer.RestDB;
import UIAdapters.MenuRecyclerViewAdapter;
import ViewModels.BranchMenuViewModel;

public class BranchViewActivity extends AppCompatActivity {

    private final String TAG = "BranchViewActivity";

    private RestDB rdb;

    private TextView branchNameTV;
    private TextView branchBusinessHrsTV;
    private TextView selectedTableTV;

    private RecyclerView menuRecyclerView;

    private BranchMenuViewModel branchMenuViewModel;
    private MenuRecyclerViewAdapter menuAdapter;
    
    private Branch branch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_view2);
        rdb = RestDB.getInstance();

        String restId = getIntent().getStringExtra(QRCode.KEY_RESTAURANT_ID);
        String branchAddr = getIntent().getStringExtra(QRCode.KEY_BRANCH_ADDRESS);
        String menuPath = getIntent().getStringExtra("menuPath");

//        Branch branch = rdb.getBranch(restId, branchAddr); // ??

        branchNameTV = (TextView) findViewById(R.id.branch_name_TV);
        branchBusinessHrsTV = (TextView) findViewById(R.id.branch_business_hrs_TV);
        selectedTableTV = (TextView) findViewById(R.id.user_selected_table_TV);

        // Menu Recycler View ref
        menuRecyclerView = (RecyclerView) findViewById(R.id.branch_menu_recycle_view);

        // Initialize ViewModel
        branchMenuViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(BranchMenuViewModel.class);


//        List<Item> menu = branch.getMenu();

        // Temp item list: ------------------------------------------------------------------
//        List<Item> items = new ArrayList<>();
//        items.add(new Item("desc1", "someImgURL", "itemName1", "kitchen", true, 20.0));
//        items.add(new Item("desc2", "someImgURL", "itemName2", "bar", false, 21.0));
        // -----------------------------------------------------------------------------------

        // Get selected branch



        // set up adapter
//        menuAdapter = new MenuRecyclerViewAdapter(this, menu);

        // set up the RecyclerView
        menuRecyclerView = (RecyclerView) findViewById(R.id.branch_menu_recycle_view);

        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuRecyclerView.setAdapter(menuAdapter);

        // TODO: 12/18/2021 Stopped here
//        branch = rdb.getBranch(getIntent()
//                .getIntExtra(QRCode.KEY_RESTAURANT_ID,-1));
    }

    @Override
    protected void onStart() {
        super.onStart();

        String selectedRestaurant = getIntent().getStringExtra(QRCode.KEY_RESTAURANT_ID);
        String selectedBranch = getIntent().getStringExtra(QRCode.KEY_BRANCH_ADDRESS);
        int selectedTable = getIntent().getIntExtra(QRCode.KEY_TABLE_NUMBER, 0);
    }
}