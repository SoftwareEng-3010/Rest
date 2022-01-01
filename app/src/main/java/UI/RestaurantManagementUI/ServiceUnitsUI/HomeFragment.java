package UI.RestaurantManagementUI.ServiceUnitsUI;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.exercise_5.R;

import API.Constants.Constants;
import API.Controllers.IManagementViewController;
import API.Views.IManagementView;
import API.Views.SwipeGestureListener;
import UI.CustomersUI.QRCodeActivity;
import UI.OnSwipeTouchListener;

public class HomeFragment extends Fragment implements SwipeGestureListener {

    private Button btnHome;

    private IManagementViewController controller;

    public HomeFragment(IManagementViewController controller) {
        this.controller = controller;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        v.setOnTouchListener(new OnSwipeTouchListener(getContext(), this));

        btnHome = ((View)container.getParent()).findViewById(R.id.btn_management_home);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        btnHome.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void onSwipeLeft() {
        Toast.makeText(getContext(), "Swipe left", Toast.LENGTH_SHORT).show();
        moveToQRCodeActivity();
    }

    @Override
    public void onSwipeRight() {
        Toast.makeText(getContext(), "Swipe right", Toast.LENGTH_SHORT).show();
        btnHome.setBackgroundColor(Color.TRANSPARENT);
        controller.onServiceButtonClicked();
    }

    @Override
    public void onSwipeTop() {
        Toast.makeText(getContext(), "Swipe up", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwipeBottom() {
        Toast.makeText(getContext(), "Swipe down", Toast.LENGTH_SHORT).show();
    }

    private void moveToQRCodeActivity() {
        Intent qrActivity = new Intent(getContext(), QRCodeActivity.class);
        qrActivity.putExtra("user_type", Constants.USER_TYPE_BRANCH_MANAGER);
        startActivity(qrActivity);
    }
}