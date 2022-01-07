package UI.RestaurantManagementUI.ServiceUnitsUI;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.example.exercise_5.R;

import java.util.List;

import API.Constants.Constants;
import API.Controllers.IServiceViewController;
import API.Views.IManagementView;
import API.Views.IServiceView;
import BusinessEntities.Table;
import BusinessLogic.ServiceViewController;
import UI.OnSwipeTouchListener;
import UIAdapters.TableGridAdapter;

public class ServiceFragment extends Fragment implements IServiceView{

    private View fragView;
    private GridView tableGrid;
    private Button btnService;
    private String branchId, restId;

    // MainView
    private IManagementView managementView;
    // Controller
    private IServiceViewController controller;

    public ServiceFragment(@NonNull IManagementView managementView) {
        this.managementView = managementView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragView = inflater.inflate(R.layout.fragment_service, container, false);

        Bundle data = getArguments();
        branchId = data.getString(Constants.KEY_BRANCH_ID);
        restId = data.getString(Constants.KEY_RESTAURANT_ID);

        controller = new ServiceViewController(managementView, this, restId, branchId);

        fragView.setOnTouchListener(
                new OnSwipeTouchListener(getContext(), controller));

        btnService = ((View)container.getParent()).findViewById(R.id.btn_management_service);

        return fragView;
    }

    @Override
    public void onResume() {
        super.onResume();
        btnService.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void loadTableDetailsFragment() {
        getParentFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .addToBackStack("ServiceFragment")
                .replace(R.id.frame_layout_management, new TableDetailsFragment(managementView))
                .commit();
    }

    @Override
    public void setupTableGridView(@NonNull List<Table> tables) {
        tableGrid = (GridView) fragView.findViewById(R.id.table_GridView); // init GridView
        tableGrid.setOnTouchListener(new OnSwipeTouchListener(getContext(), controller));

        TableGridAdapter adapter = new TableGridAdapter(getContext(), tables);
        tableGrid.setAdapter(adapter);

        // implement setOnItemClickListener event on GridView
        tableGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                controller.onTableItemClicked(tables.get(position));
            }
        });
    }
}