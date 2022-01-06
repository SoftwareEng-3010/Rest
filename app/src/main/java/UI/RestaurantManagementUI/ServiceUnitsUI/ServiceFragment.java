package UI.RestaurantManagementUI.ServiceUnitsUI;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exercise_5.R;

import java.util.ArrayList;
import java.util.List;

import API.Constants.Constants;
import API.Controllers.IManagementViewController;
import API.Controllers.IServiceViewController;
import API.Database.DatabaseRequestCallback;
import API.Views.IServiceView;
import API.Views.SwipeGestureListener;
import BusinessEntities.Branch;
import BusinessEntities.Table;
import BusinessLogic.ServiceFragmentController;
import DataAccessLayer.RestDB;
import UI.OnSwipeTouchListener;
import UIAdapters.TableGridAdapter;

public class ServiceFragment extends Fragment implements IServiceView {

    private Button btnService;
    private RestDB rdb;
    private Branch branch;
    private GridView tableGrid;
    private String branchId, restId;
    private List<Table> tables;

    private IServiceViewController controller;

    public ServiceFragment() {
        rdb = RestDB.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_service, container, false);
        Bundle b = getArguments();
        branchId = b.getString(Constants.KEY_BRANCH_ID);
        restId = b.getString(Constants.KEY_RESTAURANT_ID);
        rdb.getBranch(restId, branchId, new DatabaseRequestCallback() {
            @Override
            public void onObjectReturnedFromDB(@Nullable Object obj) {
                if(obj != null){
                    branch = (Branch) obj;
                }
            }
        });
        controller = new ServiceFragmentController(this, branch);

        tableGrid = (GridView)v.findViewById(R.id.table_GridView); // init GridView
        tableGrid.setOnTouchListener(new OnSwipeTouchListener(getContext(), controller));

        TableGridAdapter adapter = new TableGridAdapter(getContext(), tables);
        tableGrid.setAdapter(adapter);

        // implement setOnItemClickListener event on GridView
        tableGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                controller.onTableItemClicked(tables.get(position));
            }
        });


//        v.setOnTouchListener(new OnSwipeTouchListener(getContext(), this));

        btnService = ((View)container.getParent()).findViewById(R.id.btn_management_service);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        btnService.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void loadTableDetailsFragment() {
        getParentFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .addToBackStack("ServiceFragment")
                .replace(R.id.frame_layout_management, new TableDetailsFragment(controller))
                .commit();
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        btnService.setBackgroundColor(Color.WHITE);
//    }
//
//    @Override
//    public void onSwipeLeft() {
//        Toast.makeText(getContext(), "Swipe left", Toast.LENGTH_SHORT).show();
//        btnService.setBackgroundColor(Color.TRANSPARENT);
////        controller.onHomeButtonClicked();
//    }
//
//    @Override
//    public void onSwipeRight() {
//        Toast.makeText(getContext(), "Swipe right", Toast.LENGTH_SHORT).show();
//        btnService.setBackgroundColor(Color.TRANSPARENT);
//    }
//
//    @Override
//    public void onSwipeTop() {
//        Toast.makeText(getContext(), "Swipe up", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onSwipeBottom() {
//        Toast.makeText(getContext(), "Swipe down", Toast.LENGTH_SHORT).show();
//    }
}