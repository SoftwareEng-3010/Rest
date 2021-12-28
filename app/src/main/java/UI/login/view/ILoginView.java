package UI.login.view;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;

import API.IUser;

public interface ILoginView {

    public void onLoginSuccess(@NonNull IUser user, String message);
    public void onLoginFailed(String message);

    public void onCreateAccountSuccess(String message);
    public void onCreateAccountFailed(String message);
}
