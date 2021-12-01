package com.example.exercise_5;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText usrNameBox;
    private EditText passwordBox;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        mAuth = FirebaseAuth.getInstance();

        this.usrNameBox = (EditText)findViewById(R.id.user_name);
        this.passwordBox = (EditText)findViewById(R.id.password);

        loginBtn = (Button)findViewById(R.id.log_in_button);
        Button signupBtn = (Button)findViewById(R.id.sign_up_button);
        Button guestBtn = (Button)findViewById(R.id.guest_button);


//        // click listener for login button

        // click listener for  signup button
        signupBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * get values from text bars and validate. Then, update db with new user doc
             */
            public void onClick(View v) {

            }
        });

        // click listener for guest button
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

        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * get login credentials and authenticate
             */
            public void onClick(View v) {

                String username = usrNameBox.getText().toString();
                String password = passwordBox.getText().toString();

//                FirebaseUser currentUser = mAuth.;

                mAuth.signInWithEmailAndPassword(username, password);

                if (mAuth.getCurrentUser() != null) {
                    Toast.makeText(LoginActivity.this, "User email: " +
                            mAuth.getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Incorrect Credentials!!!"
                            , Toast.LENGTH_LONG).show();
                }

                mAuth.signOut();
            }
        });
    }
}
