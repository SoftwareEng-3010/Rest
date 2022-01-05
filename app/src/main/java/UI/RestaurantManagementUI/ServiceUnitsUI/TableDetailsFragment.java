package UI.RestaurantManagementUI.ServiceUnitsUI;

import android.annotation.SuppressLint;
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

import BusinessEntities.Table;
import UIAdapters.OrderArrayAdapter;

public class TableDetailsFragment extends Fragment {

    private final String TAG = "TableDetailsFragment";
    private TextView TVSelectedTable;
    private Table table;

    public TableDetailsFragment(Table table) {
        this.table = table;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_table_details, container, false);

        TVSelectedTable = (TextView) v.findViewById(R.id.text_view_table_bill_amount); // init a TextView
        if (table.getBill() != null) {
            TVSelectedTable.setText(getString(R.string.text_total_bill_amount_he) + " " + getString(R.string.nis) + table.getBillTotal());
        }

        else {
            Log.e(TAG, "table bill is null");
        }

        ListView orderList = (ListView) v.findViewById(R.id.order_list_view);

        orderList.setAdapter(new OrderArrayAdapter(
                v.getContext(),
                R.layout.layout_table_order_list,
                table.getBill().getOrders()
        ));


        return v;
    }
}