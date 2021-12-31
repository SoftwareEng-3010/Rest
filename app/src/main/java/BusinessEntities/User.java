package BusinessEntities;

import com.google.firebase.firestore.PropertyName;

import API.Models.IUser;

public class User implements IUser {

    @PropertyName("uid")
    private String id;
    private int type;
    private String email;

    public User() {}

    @Override
    public String getUid() {
        return id;
    }

    @Override
    public int getType() {
        return this.type;
    }
}
