package DataAccessLayer;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Authentication {

    private FirebaseAuth mAuth;
    private boolean authResult;

    public Authentication(){
        mAuth = FirebaseAuth.getInstance();
    }


    /**
     * signs user in using email and password
     * @param email user's email
     * @param password user's password
     * @return a boolean value representing the success of the authentication operation
     */
    public boolean signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) authResult = true;
                else authResult = false;
            }
        });
        return authResult;
    }

    /**
     * signs user up using email and password
     * @param email user's email
     * @param password user's password
     * @return a boolean value representing the success of the authorization operation
     */
    public boolean signUp(String email, String password){
        Task<AuthResult> t = mAuth.signInWithEmailAndPassword(email, password);
        t.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) authResult = true;
                else authResult = false;
            }
        });
        return authResult;
    }
}
