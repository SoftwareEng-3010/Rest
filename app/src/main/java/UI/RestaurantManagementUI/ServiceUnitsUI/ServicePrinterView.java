package UI.RestaurantManagementUI.ServiceUnitsUI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.exercise_5.R;

public class ServicePrinterView extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_service_printer, container, false);


        return v;
    }

    // clean the list every 10 seconds
    private void refreshListView(){

    }



}