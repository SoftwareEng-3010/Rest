package com.example.exercise_5;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        EditText usrNameBox = (EditText)findViewById(R.id.user_name);
        EditText passwordBox = (EditText)findViewById(R.id.password);

        Button loginBtn = (Button)findViewById(R.id.log_in_button);
        Button signupBtn = (Button)findViewById(R.id.sign_up_button);
        Button guestBtn = (Button)findViewById(R.id.guest_button);


        // click listener for login button
        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * get login credentials and authenticate
             */
            public void onClick(View v) {

            }
        });

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


}
