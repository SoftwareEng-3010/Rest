package com.example.exercise_5;

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

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";

    private FirebaseAuth mAuth;
    private EditText emailBox;
    private EditText passwordBox;
    private Button signinBtn;
    private Button signupBtn;
    private Button guestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        Toast.makeText(this, "Login: onCreate", Toast.LENGTH_SHORT).show();
        
        super.onCreate(savedInstanceState);                         // Required
        setContentView(R.layout.login_activity);                    // Connecting to login layout
        Toast.makeText(this, "Before getInstance", Toast.LENGTH_SHORT).show();

        mAuth = FirebaseAuth.getInstance();

        Toast.makeText(LoginActivity.this, "Got firebase instance", Toast.LENGTH_SHORT).show();

        this.emailBox = (EditText)findViewById(R.id.email);         // Reference to textbox
        this.passwordBox = (EditText)findViewById(R.id.password);   // Reference to textbox

        // References to buttons
        signinBtn = (Button)findViewById(R.id.signin_button);
        signupBtn = (Button)findViewById(R.id.sign_up_button);
        guestBtn = (Button)findViewById(R.id.guest_button);

        //initListeners();
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

    private void initListeners(){

        signinBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * get signin credentials and authenticate
             */
            public void onClick(View v) {

                Toast.makeText(LoginActivity.this, "Started login.onClick", Toast.LENGTH_LONG).show();
                String email = emailBox.getText().toString();
                String password = passwordBox.getText().toString();

                signIn(email, password);
//
//                mAuth.signInWithEmailAndPassword(username, password);
//
//                if (mAuth.getCurrentUser() != null) {
//                    Toast.makeText(LoginActivity.this, "User email: " +
//                            mAuth.getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(LoginActivity.this, "Incorrect Credentials!!!"
//                            , Toast.LENGTH_LONG).show();
//                }

            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * get values from text bars and validate. Then, update db with new user doc
             */
            public void onClick(View v) {

            }
        });

        guestBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * move to
             */
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Login: onStart", Toast.LENGTH_SHORT).show();
        initListeners();
    }
}
