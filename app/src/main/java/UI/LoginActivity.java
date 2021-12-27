package UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.exercise_5.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import API.Database.OnDataSentToDB;
import DataAccessLayer.RestDB;
import UI.DataActivity.DataActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String TAGCredentials = "EmailPassword";
    private static final String TAG = "LoginActivity";

    private FirebaseAuth mAuth;
    private EditText emailBox;
    private EditText passwordBox;
    private Button signInBtn;
    private Button signupBtn;
    private Button guestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.e(TAG, "onCreate()");

        mAuth = FirebaseAuth.getInstance(); // getting firebase auth instance
        RestDB.getInstance();
        // If a user is already signed in
        if (mAuth.getCurrentUser() != null) {

            moveToQRCodeActivity();
        }

        // References to TextBoxes
        emailBox = (EditText)findViewById(R.id.email);
        passwordBox = (EditText)findViewById(R.id.password);

        // References to buttons
        signInBtn = (Button)findViewById(R.id.signin_button);
        signupBtn = (Button)findViewById(R.id.sign_up_button);
        guestBtn = (Button)findViewById(R.id.guest_button);
        initListeners();

    }

    private void initListeners(){

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
                createAccount(email, password);
            }
        });

        guestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 12/14/2021 Figure out what should be done here
//                moveToRestaurantSelectionActivity(); // ?
                // Temporary intent to DataActivity
                Intent moveToDataActivity = new Intent(LoginActivity.this, DataActivity.class);
                startActivity(moveToDataActivity);
                // Don't call finish()
            }
        });
    }

    private void moveToQRCodeActivity() {
        Intent moveToQRActivity = new Intent(this, QRCodeActivity.class);
        startActivity(moveToQRActivity);
        finish();   // No need in this activity anymore.
    }

    private void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            moveToQRCodeActivity();
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.e(TAGCredentials, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            passwordBox.setText("");
                        }
                    }
                });
    }

    private void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // User is logged in and can move to the next activity

                            int userType = 0; // Determine userType!!!

                            RestDB.getInstance().addUserWithType(
                                    task.getResult().getUser(),
                                    userType,
                                    new OnDataSentToDB() {
                                        @Override
                                        public void onObjectWrittenToDB(boolean isTaskSuccessful) {
                                            if (isTaskSuccessful) {
                                                moveToQRCodeActivity();
                                            }
                                            else {
                                                Log.e(TAG, "Something went wrong writing the user into database");
//                                                mAuth.signOut();
                                            }
                                        }
                                    }
                            );
                        }
                        else {
                            // If sign in fails, display a message to the user and ask for pw.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            passwordBox.setText("");
                        }
                    }
                });
    }

}
