package UI.LoginUI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.exercise_5.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import API.Models.IBranchManagerUser;
import API.Models.IUser;
import API.Constants.Constants;
import API.Database.DatabaseRequestCallback;
import DataAccessLayer.RestDB;
import UI.CustomersUI.QRCodeActivity;
import UI.RestaurantManagementUI.ManagementView;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity implements DatabaseRequestCallback {

    private final String TAG = "SplashActivity";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        if (user == null) {
            Intent loginActivity = new Intent(this, LoginActivity.class);
            startActivity(loginActivity);
            finish();
        }

        else {

            RestDB.getInstance()
                    .getUser(user.getUid(), this);
        }
    }

    @Override
    public void onObjectReturnedFromDB(@Nullable Object obj) {
        if (obj == null) {
            Toast.makeText(this, "IUser object is null", Toast.LENGTH_SHORT).show();
            Intent loginActivity = new Intent(this, LoginActivity.class);
            startActivity(loginActivity);
            finish();
            return;
        }

        IUser user = (IUser) obj;

        if (user.getType() == Constants.USER_TYPE_CUSTOMER) {
            Intent qrActivity = new Intent(this, QRCodeActivity.class);
            startActivity(qrActivity);
            finish();
        }

        else if (user.getType() == Constants.USER_TYPE_BRANCH_MANAGER) {
            Log.e(TAG, "user has type == 1");
            moveToManagementNavigationActivity((IBranchManagerUser) obj);
        }
    }

    private void moveToManagementNavigationActivity(IBranchManagerUser manager) {
        Intent managementMainActivity = new Intent(this, ManagementView.class);
        managementMainActivity.putExtra("user_type", manager.getType());
        managementMainActivity.putExtra("manager_uid", manager.getUid());
        managementMainActivity.putExtra("branch_id", manager.getBranchDocId());
        managementMainActivity.putExtra("rest_id", manager.getRestaurantDocId());
        startActivity(managementMainActivity);
        finish();
    }
}