package UI.login.controller;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import API.Database.OnDataReceivedFromDB;
import API.Database.OnDataSentToDB;
import API.IUser;
import BusinessEntities.User;
import DataAccessLayer.RestDB;
import UI.login.view.ILoginView;
import UI.login.view.LoginActivity;

public class LoginViewController implements ILoginViewController {

    private ILoginView loginView;
    private FirebaseAuth mAuth;
    private RestDB db;

    private IUser user;

    public LoginViewController(ILoginView loginView) {
        this.loginView = loginView;
        mAuth = FirebaseAuth.getInstance();
        db = RestDB.getInstance();
    }

    @Override
    public void onLoginClicked(String email, String password) {

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            loginView.onLoginFailed("Email or Password are missing");
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            if (task.getResult().getUser() == null) {
                                loginView.onLoginFailed("task.getResult().getUser() returned null !!!");
                                return;
                            }

                            String userUid = task.getResult().getUser().getUid();

                            db.getUser(userUid, new OnDataReceivedFromDB() {
                                        @Override
                                        public void onObjectReturnedFromDB(@Nullable Object obj) {
                                            user = (IUser) obj;
                                            if (user == null) {
                                                loginView.onLoginFailed("user is null for some reason");
                                            }
                                            else {
                                                loginView.onLoginSuccess(user, "Successfully logged in as " + user.getEmail());
                                            }
                                        }
                                    });

                        }
                        else if (task.getException() != null){
                            loginView.onLoginFailed(task.getException().getMessage());
                        }
                    }
                });
    }

    @Override
    public void onSignUpClicked(String email, String password, int userLoginType) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // User is logged in and can move to the next activity
                            if (task.getResult().getUser() == null) {
                                loginView.onCreateAccountFailed("Task result returned a NULL FirebaseUser object");
                            }

                            db.addUserWithType(
                                    task.getResult().getUser(),
                                    userLoginType,
                                    new OnDataSentToDB() {
                                        @Override
                                        public void onObjectWrittenToDB(boolean isTaskSuccessful) {
                                            if (isTaskSuccessful) {
                                                loginView.onCreateAccountSuccess("New account created successfully!");
                                            }
                                            else {
                                                loginView.onCreateAccountFailed("Something went wrong writing the user into database");
                                            }
                                        }
                                    }
                            );
                        }
                        else if (task.getException() != null){
                            // If sign in fails, display a message to the user and ask for pw.
                            loginView.onCreateAccountFailed(task.getException().getMessage());
                        }
                    }
                });
    }
}
