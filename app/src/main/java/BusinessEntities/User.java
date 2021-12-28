package BusinessEntities;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.PropertyName;

import API.IUser;

public class User implements IUser {

    @PropertyName("uid")
    private String id;

    private String email;

    @PropertyName("type")
    private int userType;
    private String imageURL;

    // More fields will be added on...

    public User() {}

    public User(String email, int userType) {
        this.email = email;
        this.userType = userType;
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
        return userType;
    }

    public String getImageURL() {
        return imageURL;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userType=" + userType +
                ", email='" + email + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
