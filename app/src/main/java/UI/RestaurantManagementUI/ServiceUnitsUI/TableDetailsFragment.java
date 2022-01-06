package UI.RestaurantManagementUI.ServiceUnitsUI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exercise_5.R;

import API.Controllers.IServiceViewController;
import API.Views.SwipeGestureListener;
import BusinessEntities.Table;
import UI.OnSwipeTouchListener;
import UIAdapters.OrderArrayAdapter;

public class TableDetailsFragment extends Fragment implements SwipeGestureListener {

    private final String TAG = "TableDetailsFragment";
    private TextView TVSelectedTable;
    private IServiceViewController viewController;
    private Table table;

    public TableDetailsFragment(IServiceViewController viewController) {
        this.viewController = viewController;
        this.table = table;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_table_details, container, false);

        v.setOnTouchListener(new OnSwipeTouchListener(v.getContext(), this));

        TVSelectedTable = (TextView) v.findViewById(R.id.text_view_table_bill_amount); // init a TextView

        ListView orderList = (ListView) v.findViewById(R.id.order_list_view);

        if (null != table.getBill()) {
            String amountText = getString(R.string.text_total_bill_amount_he);
            String amountSum = getString(R.string.nis) + table.getBillTotal();
            TVSelectedTable.setText(
                    amountText
                            + ": " +
                            amountSum);

            orderList.setAdapter(new OrderArrayAdapter(
                    v.getContext(),
                    R.layout.layout_table_order_list,
                    table.getBill().getOrders()
            ));
        }
        else {
            Log.e(TAG, "Table " + table.getTableNumber() + " is null");
        }


        return v;
    }

    @Override
    public void onSwipeLeft() {
        Toast.makeText(getContext(), "Swipe Left", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwipeRight() {
        Toast.makeText(getContext(), "Swipe Right", Toast.LENGTH_SHORT).show();
    }
}