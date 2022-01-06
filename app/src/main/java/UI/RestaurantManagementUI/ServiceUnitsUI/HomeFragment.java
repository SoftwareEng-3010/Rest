package UI.RestaurantManagementUI.ServiceUnitsUI;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.exercise_5.R;

import API.Constants.Constants;
import API.Controllers.IHomeViewController;
import API.Controllers.IManagementViewController;
import API.Views.IHomeView;
import BusinessLogic.HomeViewController;
import UI.CustomersUI.QRCodeActivity;
import UI.OnSwipeTouchListener;

public class HomeFragment extends Fragment implements IHomeView {

    private Button btnHome;

    private IHomeViewController controller;
    private IManagementViewController mainController;

    public HomeFragment(@NonNull IManagementViewController mainController) {
        this.mainController = mainController;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // TODO: 1/6/2022 Append these arguments to bundle!!!
        String restId = getArguments().getString(Constants.KEY_RESTAURANT_ID);
        String branchId = getArguments().getString(Constants.KEY_BRANCH_ID);
//        controller = new HomeViewController(this, restId, branchId);
        controller = new HomeViewController(this, restId, branchId);
        v.setOnTouchListener(new OnSwipeTouchListener(getContext(), controller));
        btnHome = ((View)container.getParent()).findViewById(R.id.btn_management_home);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        btnHome.setBackgroundColor(Color.WHITE);
    }

    private void moveToQRCodeActivity() {
        Intent qrActivity = new Intent(getContext(), QRCodeActivity.class);
        qrActivity.putExtra("user_type", Constants.USER_TYPE_BRANCH_MANAGER);
        startActivity(qrActivity);
    }

    @Override
    public void startQRActivity() {
        Toast.makeText(getContext(), "Swipe left", Toast.LENGTH_SHORT).show();
        moveToQRCodeActivity();
    }

    @Override
    public void loadServiceUI() {
        Toast.makeText(getContext(), "Swipe right", Toast.LENGTH_SHORT).show();
        btnHome.setBackgroundColor(Color.TRANSPARENT);
        mainController.onServiceButtonClicked();
    }
}