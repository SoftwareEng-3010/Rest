package API.Models;

public interface IBranchManagerUser extends IUser, ICustomerUser {

    public String getBranchDocId();
    public String getSomeOtherDetails();
}
