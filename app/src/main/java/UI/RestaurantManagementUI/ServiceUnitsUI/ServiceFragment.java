package UI.RestaurantManagementUI.ServiceUnitsUI;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.exercise_5.R;

import java.util.ArrayList;
import java.util.List;

import API.Controllers.IManagementViewController;
import API.Database.DatabaseRequestCallback;
import API.Views.SwipeGestureListener;
import BusinessEntities.Branch;
import BusinessEntities.Table;
import DataAccessLayer.RestDB;
import UI.OnSwipeTouchListener;
import UIAdapters.TableGridAdapter;

public class ServiceFragment extends Fragment implements SwipeGestureListener {

    private Button btnService;
    private RestDB rdb;
    private Branch branch;
    private GridView tableGrid;
    private String branchId, restId;
    private List<Table> tables;

    private IManagementViewController controller;

    public ServiceFragment(IManagementViewController controller, String branchId, String restId) {
        this.controller = controller;
        this.branchId = branchId;
        this.restId = restId;
        rdb = RestDB.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_service, container, false);

        tableGrid = (GridView) v.findViewById(R.id.table_GridView); // init GridView

        rdb.getBranch(restId, branchId,
                new DatabaseRequestCallback() {
                    @Override
                    public void onObjectReturnedFromDB(Object obj) {

                        // When data arrives from DB - init the ListView adapter
                        branch = (Branch) obj;
                        tables = new ArrayList<>();
                        tables.addAll(branch.getTables());

                        TableGridAdapter adapter = new TableGridAdapter(getContext(), tables);
                        tableGrid.setAdapter(adapter);
                    }
                });

        // implement setOnItemClickListener event on GridView
//        tableGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//        });


        v.setOnTouchListener(new OnSwipeTouchListener(getContext(), this));

        btnService = ((View)container.getParent()).findViewById(R.id.btn_management_service);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        btnService.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void onSwipeLeft() {
        Toast.makeText(getContext(), "Swipe left", Toast.LENGTH_SHORT).show();
        btnService.setBackgroundColor(Color.TRANSPARENT);
        controller.onHomeButtonClicked();
    }

    @Override
    public void onSwipeRight() {
        Toast.makeText(getContext(), "Swipe right", Toast.LENGTH_SHORT).show();
        btnService.setBackgroundColor(Color.TRANSPARENT);
        controller.onKitchenButtonClicked();
    }

    @Override
    public void onSwipeTop() {
        Toast.makeText(getContext(), "Swipe up", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwipeBottom() {
        Toast.makeText(getContext(), "Swipe down", Toast.LENGTH_SHORT).show();
    }
}