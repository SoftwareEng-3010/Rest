package UI.RestaurantManagementUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exercise_5.R;

import API.Constants.Constants;
import DataAccessLayer.RestDB;
import UI.DataActivity.DataActivity;

public class ManagementMainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_main);

        textView = (TextView) findViewById(R.id.textView);

        String managerUid = getIntent().getStringExtra("manager_uid");
        String branchUid = getIntent().getStringExtra("branch_uid");
        int userType = getIntent().getIntExtra("user_type", -1);



        if (userType == -1) {
            Toast.makeText(this, "userType == -1", Toast.LENGTH_SHORT).show();
        }

        setTextOnUI(userType);

//        if (branchUid == null) {
//            Intent dataActivity = new Intent(this, DataActivity.class);
//            startActivity(dataActivity);
//        }

    }



    private void setTextOnUI(int userType) {
        switch (userType) {
            case Constants
                    .USER_TYPE_BRANCH_MANAGER:
                textView.setText("Hello, Manager!");
                break;

            case Constants
                    .USER_TYPE_BRANCH_MANAGER2:
                textView.setText("Hello, Second Manager!");
                break;

            case Constants
                    .USER_TYPE_SERVICE:
                textView.setText("Hello, Waiter / Waitress!");
                break;

            case Constants
                    .USER_TYPE_KITCHEN:
                textView.setText("Hello, Kitchen!");
                break;
        }
    }
}