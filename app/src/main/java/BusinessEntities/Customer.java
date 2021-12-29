package BusinessEntities;

import com.google.firebase.firestore.PropertyName;

import API.BusinessEntitiesInterface.Auth.ICustomerUser;
import API.BusinessEntitiesInterface.Auth.IUser;

public class Customer implements ICustomerUser {

    @PropertyName("uid")
    private String id;

    private String email;

    @PropertyName("type")
    private int userType;
    private String imageURL;

    // More fields will be added on...

    public Customer() {}

    public Customer(String email, int userType) {
        this.email = email;
        this.userType = userType;
    }

    @Override
    public String getUid() {
        return id;
    }

    @Override
    public int getType() {
        return userType;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    public String getImageURL() {
        return imageURL;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", userType=" + userType +
                ", email='" + email + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
