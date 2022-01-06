package UI.RestaurantManagementUI.ServiceUnitsUI;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.exercise_5.R;

import API.Constants.Constants;
import API.Controllers.IKitchenFragmentController;
import API.Controllers.IManagementViewController;
import API.Views.IKitchenView;
import API.Views.IManagementView;
import API.Views.SwipeGestureListener;
import BusinessEntities.Kitchen;
import BusinessEntities.Printer;
import BusinessLogic.KitchenViewController;
import UI.OnSwipeTouchListener;
import UIAdapters.OrdersRecyclerViewAdapter;
import ViewModels.OrderViewModel;

public class KitchenFragment extends Fragment implements IKitchenView {

    private IManagementView managementView;
    private IKitchenFragmentController controller;
    private OrderViewModel viewModel;

    private RecyclerView ordersRecyclerView;

    private Button btnKitchen;

    private Printer kitchenPrinter;

    public KitchenFragment(@NonNull IManagementView managementView) {
        this.managementView = managementView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_kitchen, container, false);

        String restId = getArguments().getString(Constants.KEY_RESTAURANT_ID);
        String branchId = getArguments().getString(Constants.KEY_BRANCH_ID);

        this.controller = new KitchenViewController(managementView, this, restId, branchId);

        v.setOnTouchListener(new OnSwipeTouchListener(getContext(), controller));
        btnKitchen = ((View)container.getParent()).findViewById(R.id.btn_management_kitchen);

        ordersRecyclerView = (RecyclerView) v.findViewById(R.id.kitchen_recycler_view);

        viewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance()
                .create(OrderViewModel.class);

        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


//        IKitchenFragmentController controller = new KitchenFragmentController();
        // Set "Context" to the kitchenPrinter to let it
        // know where it should print
        kitchenPrinter = new Printer();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        btnKitchen.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void setupUI() {


//        ordersRecyclerView.setAdapter(
//                new OrdersRecyclerViewAdapter(
//                        getContext(),
//
//                        ));
    }

    @Override
    public void setupServiceUI() {

    }
}