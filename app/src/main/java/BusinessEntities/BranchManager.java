package BusinessEntities;

import com.google.firebase.firestore.PropertyName;

import API.Models.IBranchManagerUser;

public class BranchManager implements IBranchManagerUser {

    @PropertyName("uid")
    private String id;
    private int type;
    private String email;
    @PropertyName("branch_id")
    private String branchDocIdBeingManaged;
    @PropertyName("rest_id")
    private String restDocIdBeingManaged;

    public BranchManager() {}

    @Override
    @PropertyName("rest_id")
    public String getRestaurantDocId() {
        return this.restDocIdBeingManaged;
    }

    @Override
    @PropertyName("branch_id")
    public String getBranchDocId() {
        return branchDocIdBeingManaged;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getSomeOtherDetails() {
        return "Some other details will be added on...";
    }

    @Override
    public String getUid() {
        return id;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String getImageURL() {
        return null;
    }
}
