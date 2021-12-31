package UI.RestaurantManagementUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.exercise_5.R;

import API.Constants.Constants;
import API.Views.SwipeGestureListener;
import BusinessEntities.Branch;
import UI.CustomersUI.QRCodeActivity;
import UI.DataActivity.DataActivity;
import UI.OnSwipeTouchListener;

public class ManagementMainActivity extends AppCompatActivity implements IManagementView, SwipeGestureListener {

    private FrameLayout frameLayout;

    private IManagementViewController viewController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_main);

        String managerUid = getIntent().getStringExtra("manager_uid");
        String branchId = getIntent().getStringExtra("branch_id");
        String restId = getIntent().getStringExtra("rest_id");

        boolean manager_is_logged_in_as_customer = getIntent().getBooleanExtra("is_manager_logged_in_as_customer", false);

        if (manager_is_logged_in_as_customer) {
            moveToQRCodeActivity();
        }


        frameLayout = (FrameLayout) findViewById(R.id.frame_layout_management);

        frameLayout.setOnTouchListener(new OnSwipeTouchListener(this, this));

        viewController = new ManagementViewController(this, restId, branchId);
    }

    private void moveToDataActivity() {
//        if (branchUid == null) {
            Intent dataActivity = new Intent(this, DataActivity.class);
            startActivity(dataActivity);
//        }
    }

    @Override
    public void onSwipeLeft() {
        moveToQRCodeActivity();
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


    @Override
    public void beforeMoveToHomeScreen(Branch branch) {
        Toast.makeText(this, "Branch address: " + branch.getAddress(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void beforeMoveToServiceScreen() {

    }

    @Override
    public void beforeMoveToKitchen() {

    }

    @Override
    public void onDataFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    private void moveToQRCodeActivity() {
        Toast.makeText(this, "Swipe left", Toast.LENGTH_SHORT).show();
        Intent qrActivity = new Intent(this, QRCodeActivity.class);
        qrActivity.putExtra("user_type", Constants.USER_TYPE_BRANCH_MANAGER);
        startActivity(qrActivity);
    }
}