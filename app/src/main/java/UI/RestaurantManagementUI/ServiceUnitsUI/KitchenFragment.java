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

import com.example.exercise_5.R;

import java.util.ArrayList;

import API.Constants.Constants;
import API.Controllers.IKitchenFragmentController;
import API.IOrderListener;
import API.Models.IOrder;
import API.Views.IKitchenView;
import API.Views.IManagementView;
import BusinessEntities.Printer;
import BusinessLogic.KitchenViewController;
import UI.OnSwipeTouchListener;
import UIAdapters.OrdersRecyclerViewAdapter;
import ViewModels.OrdersViewModel;

public class KitchenFragment extends Fragment implements IKitchenView, IOrderListener {

    private IManagementView managementView;
    private IKitchenFragmentController controller;
    private OrdersViewModel viewModel;

    private RecyclerView ordersRecyclerView;
    private OrdersRecyclerViewAdapter ordersRecyclerViewAdapter;
    private Button btnKitchen;

//    private Printer kitchenPrinter;

    public KitchenFragment(@NonNull IManagementView managementView) {
        this.managementView = managementView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_kitchen, container, false);

        ordersRecyclerView = (RecyclerView) v.findViewById(R.id.kitchen_recycler_view);

        btnKitchen = ((View)container.getParent()).findViewById(R.id.btn_management_kitchen);


        viewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance()
                .create(OrdersViewModel.class);

        ordersRecyclerViewAdapter = new OrdersRecyclerViewAdapter(getContext());


        v.setOnTouchListener(new OnSwipeTouchListener(getContext(), controller));
//        ordersRecyclerView.setOnTouchListener(new OnSwipeTouchListener(getContext(), controller));

        String restId = getArguments().getString(Constants.KEY_RESTAURANT_ID);
        String branchId = getArguments().getString(Constants.KEY_BRANCH_ID);
        this.controller = new KitchenViewController(managementView, this, restId, branchId);


        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ordersRecyclerView.setAdapter(new OrdersRecyclerViewAdapter(getContext()));

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

    @Override
    public void onOrderReceived(@NonNull IOrder order) {
        ordersRecyclerViewAdapter.addOrder(order);
        ordersRecyclerView.setAdapter(ordersRecyclerViewAdapter);
    }
}