package UI.RestaurantManagementUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exercise_5.R;

import API.Constants.Constants;
import API.Views.IManagementView;
import API.Views.SwipeGestureListener;
import UI.CustomersUI.QRCodeActivity;
import UI.DataActivity.DataActivity;
import UI.OnSwipeTouchListener;

public class ManagementMainActivity extends AppCompatActivity implements IManagementView, SwipeGestureListener {

    private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_main);

        String managerUid = getIntent().getStringExtra("manager_uid");
        String branchUid = getIntent().getStringExtra("branch_uid");
        int userType = getIntent().getIntExtra("user_type", -1);

        if (userType == -1) {
            Toast.makeText(this, "userType == -1", Toast.LENGTH_SHORT).show();
        }

        frameLayout = (FrameLayout) findViewById(R.id.frame_layout_management);

        frameLayout.setOnTouchListener(new OnSwipeTouchListener(this, this));
    }

    @Override
    public void navigateToHomeScreen() {

    }

    @Override
    public void navigateToServiceScreen() {

    }

    @Override
    public void navigateToKitchenScreen() {

    }

    private void moveToDataActivity() {
//        if (branchUid == null) {
            Intent dataActivity = new Intent(this, DataActivity.class);
            startActivity(dataActivity);
//        }
    }

    @Override
    public void onSwipeLeft() {
        Toast.makeText(this, "Swipe left", Toast.LENGTH_SHORT).show();
        Intent qrActivity = new Intent(this, QRCodeActivity.class);
        qrActivity.putExtra("user_type", Constants.USER_TYPE_BRANCH_MANAGER);
        startActivity(qrActivity);
    }

    @Override
    public void onSwipeRight() {
        Toast.makeText(this, "Swipe right", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwipeTop() {
        Toast.makeText(this, "Swipe up", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwipeBottom() {
        Toast.makeText(this, "Swipe down", Toast.LENGTH_SHORT).show();
    }
}