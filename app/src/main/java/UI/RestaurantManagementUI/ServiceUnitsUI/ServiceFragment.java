package UI.RestaurantManagementUI.ServiceUnitsUI;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
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

public class ServiceFragment extends Fragment implements IServiceView, SwipeGestureListener {

    private View fragView;
    private GridView tableGrid;
    private Button btnService;
    private String branchId, restId;

    private IServiceViewController controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragView = inflater.inflate(R.layout.fragment_service, container, false);

        Bundle b = getArguments();
        branchId = b.getString(Constants.KEY_BRANCH_ID);
        restId = b.getString(Constants.KEY_RESTAURANT_ID);

        controller = new ServiceFragmentController(this, restId, branchId);

        fragView.setOnTouchListener(new OnSwipeTouchListener(getContext(), this));

        btnService = ((View)container.getParent()).findViewById(R.id.btn_management_service);

        return fragView;
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

    @Override
    public void setupTableGridView(@NonNull List<Table> tables) {
        tableGrid = (GridView) fragView.findViewById(R.id.table_GridView); // init GridView
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
    }

    @Override
    public void onSwipeLeft() {

    }

    @Override
    public void onSwipeRight() {

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