package UI.DataActivity.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exercise_5.R;

import BusinessEntities.Address;
import UI.DataActivity.Controller.DataViewController;

public class CreateBranchFragment extends Fragment {

    private final String TAG = "CreateBranchFragment";

    private DataViewController controller;

    private EditText editTextBranchCity;
    private EditText editTextBranchStreet;
    private EditText editTextBranchBuildingNumber;
    private Switch switchIsKosher;
    private Button btnCreateBranch;
    private ProgressBar progressBar;

    public CreateBranchFragment(DataViewController controller) {
        this.controller = controller;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_create_branch, container, false);

        Bundle dataBundle = getArguments();

        TextView tvRestName = (TextView) view.findViewById(R.id.tvRestaurantNameCreateBranchFrag);
        if (dataBundle != null) {
            Log.e(TAG, "Bundle given from CreateRestaurantFragment:\n" + dataBundle.toString());
            String restName = dataBundle.getString("rest_name");
            tvRestName.setText("Create a new branch for " + restName);
        }

        editTextBranchCity = (EditText) view.findViewById(R.id.editTextCity);
        editTextBranchStreet = (EditText) view.findViewById(R.id.edit_text_login_password);
        editTextBranchBuildingNumber = (EditText) view.findViewById(R.id.editTextBuildingNumber);
        switchIsKosher = (Switch) view.findViewById(R.id.switchIsKosher);
        btnCreateBranch = (Button) view.findViewById(R.id.buttonCreateBranch);
        progressBar = (ProgressBar) view.findViewById(R.id.loading);


        btnCreateBranch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int errorColor = R.color.design_default_color_error;

                        if (editTextBranchCity.getText().toString().isEmpty()) {
                            editTextBranchCity.setBackgroundColor(getResources().getColor(errorColor));
                        }
                        else if(editTextBranchStreet.getText().toString().isEmpty()) {
                            editTextBranchStreet.setBackgroundColor(getResources().getColor(errorColor));
                        }
                        else if(editTextBranchBuildingNumber.getText().toString().isEmpty()) {
                            editTextBranchBuildingNumber.setBackgroundColor(getResources().getColor(errorColor));
                        }
                        else {
                            String city = editTextBranchCity.getText().toString();
                            String street = editTextBranchStreet.getText().toString();
                            String buildingNumber = editTextBranchBuildingNumber.getText().toString();
                            boolean isKosher = switchIsKosher.getShowText();
                            controller.onBranchEditFinished(new Address(city, street, buildingNumber), isKosher);
                        }
                    }
                }
        );



        return view;
    }
}