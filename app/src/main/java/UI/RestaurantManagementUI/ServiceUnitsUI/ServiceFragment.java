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
import UI.OnSwipeTouchListener;

public class ServiceFragment extends Fragment implements SwipeGestureListener {

    private Button btnService;

    private IManagementViewController controller;

    public ServiceFragment(IManagementViewController controller) {
        this.controller = controller;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_service, container, false);

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