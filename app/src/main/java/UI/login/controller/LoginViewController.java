package UI.login.controller;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import API.BusinessEntitiesInterface.Auth.ICustomerUser;
import API.Constants.Constants;
import API.Database.DatabaseRequestCallback;
import API.Database.OnDataSentToDB;
import API.BusinessEntitiesInterface.Auth.IBranchManagerUser;
import API.BusinessEntitiesInterface.Auth.IUser;
import DataAccessLayer.RestDB;
import UI.login.view.ILoginView;

public class LoginViewController implements ILoginViewController {

    private ILoginView loginView;
    private FirebaseAuth mAuth;
    private RestDB db;

    public LoginViewController(ILoginView loginView) {
        this.loginView = loginView;
        mAuth = FirebaseAuth.getInstance();
        db = RestDB.getInstance();
    }

    @Override
    public void onLoginClicked(String email, String password) {

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            loginView.onLoginFailed("Email or Password are missing");
            return;
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

                            db.getUser(userUid, new DatabaseRequestCallback() {
                                        @Override
                                        public void onObjectReturnedFromDB(@Nullable Object obj) {
                                            if (null == obj) loginView.onLoginFailed("Object came back null from database");
                                            IUser usr = (IUser) obj;

                                            if (usr.getType() == Constants.USER_TYPE_BRANCH_MANAGER) {
                                                IBranchManagerUser manager = (IBranchManagerUser)obj;
                                                loginView.onLoginSuccess(manager, "Successfully logged in as a Manager\n" + manager.getEmail());
                                            }
                                            else if (usr.getType() == Constants.USER_TYPE_CUSTOMER){
                                                ICustomerUser customer = (ICustomerUser) obj;
                                                loginView.onLoginSuccess(customer, "Successfully logged in as " + customer.getEmail());
                                            }
                                            else if (usr.getType() >= Constants.USER_TYPE_BRANCH_MANAGER) {
                                                IUser user = (IUser) obj;
                                                loginView.onLoginSuccess(user, "Successfully logged in as " + user.getType());
                                            }
                                            else {
                                                loginView.onLoginFailed("Object is not an instance of IUser");
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

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            loginView.onLoginFailed("Email or Password are missing");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Customer is logged in and can move to the next activity
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
