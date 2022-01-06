package UI.RestaurantManagementUI.ServiceUnitsUI;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.exercise_5.R;

import API.Controllers.IManagementViewController;
import API.Views.SwipeGestureListener;
import BusinessEntities.Printer;
import UI.OnSwipeTouchListener;

public class KitchenFragment extends Fragment implements SwipeGestureListener {

    private Button btnKitchen;

    private IManagementViewController controller;

    private Printer kitchenPrinter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_kitchen, container, false);

        v.setOnTouchListener(new OnSwipeTouchListener(getContext(), this));

        btnKitchen = ((View)container.getParent()).findViewById(R.id.btn_management_kitchen);

        // Set "Context" to the kitchenPrinter to let it
        // know where it should print
        kitchenPrinter = new Printer(getContext());

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        btnKitchen.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void onSwipeLeft() {
        Toast.makeText(getContext(), "Swipe left", Toast.LENGTH_SHORT).show();
        btnKitchen.setBackgroundColor(Color.TRANSPARENT);
        controller.onServiceButtonClicked();
    }

    @Override
    public void onSwipeRight() {

    }
}