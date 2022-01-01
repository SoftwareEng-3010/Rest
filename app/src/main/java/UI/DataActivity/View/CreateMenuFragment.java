package UI.DataActivity.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.exercise_5.R;

import java.util.ArrayList;
import java.util.List;

import BusinessEntities.Item;
import UI.DataActivity.Controller.DataViewController;
import UIAdapters.MenuRecyclerViewAdapter;
import ViewModels.MenuViewModel;

public class CreateMenuFragment extends Fragment {

    private final String TAG = "CreateMenuFragment";

    private DataViewController viewController;

    private EditText editTextItemName;
    private EditText editTextItemDescription;
    private EditText editTextItemPrice;
    private RecyclerView itemsRecyclerView;

    private List<Item> itemList;

    private Button btnAddItemToMenu;


    public CreateMenuFragment(DataViewController controller) {
        this.viewController = controller;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_menu, container, false);

        editTextItemName = (EditText) view.findViewById(R.id.editTextItemNameCreateMenuFrag);
        editTextItemDescription = (EditText) view.findViewById(R.id.editTextItemDescriptionCreateMenuFrag);
        editTextItemPrice = (EditText) view.findViewById(R.id.editTextItemPriceCreateMenuFrag);

        btnAddItemToMenu = (Button) view.findViewById(R.id.buttonAddItemToMenu);

        itemsRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewCreateMenuFrag);


        MenuViewModel menuViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())
                .create(MenuViewModel.class);

        // set up adapter
        itemList = new ArrayList<>();
        MenuRecyclerViewAdapter menuAdapter = new MenuRecyclerViewAdapter(getContext(), itemList);

        // set up the RecyclerView
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemsRecyclerView.setAdapter(menuAdapter);

        btnAddItemToMenu.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int errorColor = R.color.design_default_color_error;

                        if (editTextItemName.getText().toString().isEmpty()) {
                            editTextItemName.setBackgroundColor(getResources().getColor(errorColor));
                        } else if (editTextItemPrice.getText().toString().isEmpty()) {
                            editTextItemPrice.setBackgroundColor(getResources().getColor(errorColor));
                        } else {

                            String name = editTextItemName.getText().toString();
                            String description = editTextItemDescription.getText().toString();
                            double price = Double.parseDouble(editTextItemPrice.getText().toString());
//                            Bundle dataBundle = getArguments(); // Extract restaurant info from bundle
//                             and write the restaurant into database
//                            dataBundle.putString("branch_city", city);
//                            dataBundle.putString("branch_street", street);
//                            dataBundle.putString("branch_building_number", buildingNumber);
//                            dataBundle.putBoolean("branch_is_kosher", isKosher);

                            // TODO: 12/25/2021 Create another ItemsAdapter for this task maybe.
                            // This adapter is not good for presenting the items in this fragment.
                            menuAdapter.addItem(new Item(name, description, null, null, true, price));
                            itemsRecyclerView.setAdapter(menuAdapter);
                        }
                    }
                }
        );

        return view;
    }
}