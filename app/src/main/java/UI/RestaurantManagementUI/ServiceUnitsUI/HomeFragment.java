package UI.RestaurantManagementUI.ServiceUnitsUI;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.exercise_5.R;

import API.Constants.Constants;
import API.Views.SwipeGestureListener;
import UI.CustomersUI.QRCodeActivity;
import UI.OnSwipeTouchListener;

public class HomeFragment extends Fragment implements SwipeGestureListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        v.setOnTouchListener(new OnSwipeTouchListener(getContext(), this));

        return v;
    }

    @Override
    public void onSwipeLeft() {
        Toast.makeText(getContext(), "Swipe left", Toast.LENGTH_SHORT).show();
        moveToQRCodeActivity();
    }

    @Override
    public void onSwipeRight() {
        Toast.makeText(getContext(), "Swipe right", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.frame_layout_management, new ServiceFragment())
                .commit();
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