package UI.DataActivity.View;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.exercise_5.R;

import java.util.HashMap;
import java.util.Map;

import BusinessEntities.Restaurant;
import UI.DataActivity.Controller.DataViewController;
import UI.DataActivity.DataActivity;

public class CreateRestaurantFragment extends Fragment implements DataEditSubView {

    private DataViewController viewController;

    private Button createRestaurant;
    private ProgressBar progressBar;
    private EditText editTextRestaurantName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_create_restaurant, container, false);

        createRestaurant = (Button) view.findViewById(R.id.buttonCreateRestaurant);
        progressBar = (ProgressBar) view.findViewById(R.id.loading);
        editTextRestaurantName = (EditText) view.findViewById(R.id.editTextRestaurantName);

        createRestaurant.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (editTextRestaurantName.getText().toString().isEmpty())
                            Toast.makeText(getContext(), "Please fill in Restaurant name field",
                                    Toast.LENGTH_LONG).show();
                        else {

                        }
                    }
                }
        );
        return view;
    }

    @Override
    public void nextFragment(Bundle dataBundle) {

        Fragment createBranchFragment = new CreateBranchFragment();
        createBranchFragment.setArguments(dataBundle);

        getParentFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .addToBackStack("CreateRestaurantFragment")
                .replace(R.id.frameLayoutDataActivity, createBranchFragment)
                .commit();
    }
}