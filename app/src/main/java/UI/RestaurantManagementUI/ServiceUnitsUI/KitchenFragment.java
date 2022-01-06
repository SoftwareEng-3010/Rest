package UI.RestaurantManagementUI.ServiceUnitsUI;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.exercise_5.R;

import API.Controllers.IKitchenFragmentController;
import API.Controllers.IManagementViewController;
import API.Views.IKitchenView;
import API.Views.IManagementView;
import API.Views.SwipeGestureListener;
import BusinessEntities.Kitchen;
import BusinessEntities.Printer;
import BusinessLogic.KitchenViewController;
import UI.OnSwipeTouchListener;

public class KitchenFragment extends Fragment implements IKitchenView {

    private IManagementView managementView;
    private IKitchenFragmentController controller;

    private Button btnKitchen;

    private Printer kitchenPrinter;

    public KitchenFragment(@NonNull IManagementView managementView) {
        this.managementView = managementView;
        this.controller = new KitchenViewController(managementView, this, null, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_kitchen, container, false);


        v.setOnTouchListener(new OnSwipeTouchListener(getContext(), controller));
        btnKitchen = ((View)container.getParent()).findViewById(R.id.btn_management_kitchen);

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

    }

    @Override
    public void setupServiceUI() {

    }
}