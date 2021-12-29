package UI.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.exercise_5.R;
import com.google.firebase.auth.FirebaseAuth;

import API.BusinessEntitiesInterface.Auth.IBranchManagerUser;
import API.BusinessEntitiesInterface.Auth.IUser;
import DataAccessLayer.RestDB;
import UI.CustomersUI.QRCodeActivity;
import UI.RestaurantManagementUI.ManagementMainActivity;
import UI.login.controller.ILoginViewController;
import UI.login.controller.LoginViewController;

public class LoginActivity extends AppCompatActivity implements ILoginView{

    private static final String TAGCredentials = "EmailPassword";
    private static final String TAG = "LoginActivity";

    private FirebaseAuth mAuth;
    private EditText emailBox;
    private EditText passwordBox;
    private Button signInBtn;
    private Button signupBtn;
    private RadioButton radioBtnRegularUser;
    private RadioButton radioBtnManagerUser;
    private TextView textPromptLoginType;
    private ProgressBar progressBar;

    private int userLoginType; // Default login (0) will be for regular users, not branch owners.

    private ILoginViewController loginViewController;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.e(TAG, "onCreate()");

        mAuth = FirebaseAuth.getInstance(); // getting firebase auth instance
        RestDB.getInstance();

        // If a user is already signed in
        if (mAuth.getCurrentUser() != null) {
            moveToCustomerUI();
        }

        this.loginViewController = new LoginViewController(this);

        // References to TextBoxes
        emailBox = (EditText)findViewById(R.id.edit_text_login_email);
        passwordBox = (EditText)findViewById(R.id.edit_text_login_password);

        // References to layout views
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_login_activity);
        textPromptLoginType = (TextView) findViewById(R.id.text_view_login_as_restaurant_owner);

        hideProgressBar();

        initListeners();
    }

    private void initListeners(){
        signInBtn = (Button)findViewById(R.id.button_sign_in);
        signupBtn = (Button)findViewById(R.id.button_sign_up);

        radioBtnRegularUser = (RadioButton) findViewById(R.id.radio_button_regular_user);
        radioBtnManagerUser = (RadioButton) findViewById(R.id.radio_button_restaurant_manager);

        radioBtnRegularUser.setChecked(true); // Default radio button checked is for regular users.

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sign in
                String email = emailBox.getText().toString();
                String password = passwordBox.getText().toString();

                signIn(email, password);
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sign up
                String email = emailBox.getText().toString();
                String password = passwordBox.getText().toString();

                createAccount(email, password, userLoginType);
            }
        });

        radioBtnRegularUser.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userLoginType = getCheckRadioButtonIndex();
                    }
                }
        );

        radioBtnManagerUser.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userLoginType = getCheckRadioButtonIndex();
                    }
                }
        );
    }

    private void moveToCustomerUI() {
        Intent moveToQRActivity = new Intent(this, QRCodeActivity.class);
        startActivity(moveToQRActivity);
        finish();   // No need in this activity anymore.
    }

    private void createAccount(String email, String password, int userLoginType) {

        showProgressBar();

        loginViewController.onSignUpClicked(email, password, userLoginType);
    }

    @Override
    public void onLoginSuccess(@NonNull IUser user, String message) {

        hideProgressBar();

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        final int REGULAR_USER = 0,
                MANAGER_USER = 1;

        if (getCheckRadioButtonIndex() == REGULAR_USER) {
            // Regular customer user: Navigate to QRActivity
            moveToCustomerUI();
        }

        else if(getCheckRadioButtonIndex() == MANAGER_USER) {
            // Branch manager user is connected: Navigate to Branch management UI
            moveToBranchManagementUI((IBranchManagerUser) user);
        }

        else {
            Toast.makeText(this, "Something went wrong determining user type", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLoginFailed(String message) {
        passwordBox.setText("");
        hideProgressBar();

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateAccountSuccess(String message) {
        mAuth.signOut(); // Make sure that login will work again.
        // This is the only .signOut() usage so far
        hideProgressBar();
        // Sign in again immediately:
        signIn(emailBox.getText().toString(), passwordBox.getText().toString());

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateAccountFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        hideProgressBar();
    }

    private void moveToBranchManagementUI(@Nullable IBranchManagerUser user) {
//        Toast.makeText(this, "Should now navigate to BranchManagementUI", Toast.LENGTH_SHORT).show();

        if (user == null)
            Toast.makeText(this,
                    "Something went wrong... " +
                            "IBranchManager is NULL and should not be!",
                    Toast.LENGTH_LONG).show();

        Intent moveToManagementActivity = new Intent(this, ManagementMainActivity.class);

        moveToManagementActivity.putExtra("manager_uid", user.getUid());
        moveToManagementActivity.putExtra("user_type", user.getType());
        moveToManagementActivity.putExtra("manager_branch_id", user.getBranchDocId());

        startActivity(moveToManagementActivity);
        finish();
    }


    private void signIn(String email, String password) {

        showProgressBar();
        loginViewController.onLoginClicked(email, password);
    }

    private int getCheckRadioButtonIndex() {

        if (radioBtnRegularUser.isChecked()) {
            Log.e(TAG, "Customer checked regular user login button");
            textPromptLoginType.setVisibility(View.INVISIBLE);
            return 0;
        }
        else if (radioBtnManagerUser.isChecked()) {
            textPromptLoginType.setVisibility(View.VISIBLE);
            Log.e(TAG, "Customer checked restaurant manager login button");
            return 1;
        }
        else {
            Log.e(TAG, "RadioGroup default. Returns -1 !!!");
            return -1;
        }
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
