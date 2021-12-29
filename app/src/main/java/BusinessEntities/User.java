package BusinessEntities;

import com.google.firebase.firestore.PropertyName;

import API.BusinessEntitiesInterface.Auth.IUser;

public class User implements IUser {

    @PropertyName("uid")
    private String id;
    private int type;
    private String email;

    public User() {}

    public User(int userType) {
        this.type = userType;
    }

    @Override
    public String getUid() {
        return id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public int getType() {
        return this.type;
    }
}
