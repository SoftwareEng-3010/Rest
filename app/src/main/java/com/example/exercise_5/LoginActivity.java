package com.example.exercise_5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import BusinessLogic.Restaurant;
import DataAccessLayer.RestDB;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";

    private FirebaseAuth mAuth;
    private EditText emailBox;
    private EditText passwordBox;
    private Button signinBtn;
    private Button signupBtn;
    private Button guestBtn;

    // private Authentication authenticator;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);                         // Required
        setContentView(R.layout.login_activity);                    // Connecting to login layout
        Toast.makeText(this, "Login: onCreate", Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "Before getInstance", Toast.LENGTH_SHORT).show();

        mAuth = FirebaseAuth.getInstance();
        //authenticator = new Authentication();

        Toast.makeText(LoginActivity.this, "Got firebase instance", Toast.LENGTH_SHORT).show();

        if (mAuth.getCurrentUser() != null) {
            // if a user is already signed in
            Intent moveToRestSelector = new Intent(this, RestSelector.class);
            moveToRestSelector.putExtra("UserEmail", mAuth.getCurrentUser().getEmail());
//            moveToRestSelector.putExtra("UserName", mAuth.getCurrentUser().getDisplayName());
            startActivity(moveToRestSelector);
        }

        // References to textboxes
        emailBox = (EditText)findViewById(R.id.email);
        passwordBox = (EditText)findViewById(R.id.password);

        // References to buttons
        signinBtn = (Button)findViewById(R.id.signin_button);
        signupBtn = (Button)findViewById(R.id.sign_up_button);
        guestBtn = (Button)findViewById(R.id.guest_button);

    }

    private void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Email: "+ user.getEmail(), Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
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
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Email: "+ user.getEmail(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed: " + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void initListeners(){

        signinBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * get signin credentials and authenticate
             */
            public void onClick(View v) {

                Toast.makeText(LoginActivity.this, "Started login.onClick", Toast.LENGTH_SHORT).show();
                String email = emailBox.getText().toString();
                String password = passwordBox.getText().toString();
                signIn(email, password);
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * get values from text bars and validate. Then, update db with new user doc
             */
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Started createAccount.onClick", Toast.LENGTH_SHORT).show();
                String email = emailBox.getText().toString();
                String password = passwordBox.getText().toString();
                createAccount(email, password);
            }
        });

        guestBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * move to
             */
            public void onClick(View v) {
                RestDB restDB = new RestDB();
                ArrayList<Restaurant> restaurants = new ArrayList<>();
                restDB.getRestaurants(restaurants);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initListeners();
    }
}
