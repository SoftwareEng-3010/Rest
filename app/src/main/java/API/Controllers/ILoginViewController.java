package API.Controllers;

public interface ILoginViewController {

    public void onLoginClicked(String email, String password);

    public void onSignUpClicked(String email, String password, int userLoginType);
}
