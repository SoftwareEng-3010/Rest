package UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.exercise_5.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import API.BusinessEntitiesInterface.Auth.IBranchManagerUser;
import API.BusinessEntitiesInterface.Auth.ICustomerUser;
import API.BusinessEntitiesInterface.Auth.IUser;
import API.BusinessEntitiesInterface.IServiceUnit;
import API.Database.DatabaseRequestCallback;
import DataAccessLayer.RestDB;
import UI.RestaurantManagementUI.ManagementMainActivity;
import UI.login.view.LoginActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity implements DatabaseRequestCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

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
        }

        IBranchManagerUser manager = (IBranchManagerUser) obj;
        if (obj != null){
            Toast.makeText(this, "MANAGER", Toast.LENGTH_SHORT).show();
        }
        else if(obj instanceof IBranchManagerUser) {
            Toast.makeText(this, "CUSTOMER", Toast.LENGTH_SHORT).show();
        }
        else if (obj instanceof IServiceUnit) {
            Toast.makeText(this, "SERVICE_UNIT", Toast.LENGTH_SHORT).show();
        }
        else if (obj instanceof IUser) {
            Toast.makeText(this, "IUSER", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "NO INSTANCE OF IUSER", Toast.LENGTH_SHORT).show();
        }
    }

    private void moveToManagementNavigationActivity(int userType) {
        Intent managementNavigationActivity = new Intent(this, ManagementMainActivity.class);
        managementNavigationActivity.putExtra("user_type", userType);
        startActivity(managementNavigationActivity);
        finish();

    }
}