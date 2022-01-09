package UI.RestaurantManagementUI.ServiceUnitsUI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exercise_5.R;

import API.Views.IManagementView;
import API.Views.ITableDetailsView;
import API.SwipeGestureListener;
import BusinessEntities.Table;
import UI.OnSwipeTouchListener;

public class TableDetailsView extends Fragment implements ITableDetailsView, SwipeGestureListener {

    private final String TAG = "TableDetailsView";
    private TextView TVSelectedTable;
    private View view;
    private IManagementView managementView;

    public TableDetailsView(IManagementView managementView) {
        this.managementView = managementView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_table_details, container, false);

        view.setOnTouchListener(new OnSwipeTouchListener(view.getContext(), this));

        return view;
    }

    @Override
    public void onSwipeLeft() {
        Toast.makeText(getContext(), "Swipe Left", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwipeRight() {
        Toast.makeText(getContext(), "Swipe Right", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadTableDetails(@NonNull Table table) {
        if (view == null) return;
        TVSelectedTable = (TextView) view.findViewById(R.id.text_view_table_bill_amount); // init a TextView

        ListView orderList = (ListView) view.findViewById(R.id.order_list_view);

        if (null != table.getBill()) {
            String amountText = getString(R.string.text_total_bill_amount_he);
            String amountSum = getString(R.string.nis) + table.getBillTotal();
            TVSelectedTable.setText(
                    amountText
                            + ": " +
                            amountSum);
        }
        else {
            Log.e(TAG, "Table " + table.getTableNumber() + " is null");
        }
    }
}