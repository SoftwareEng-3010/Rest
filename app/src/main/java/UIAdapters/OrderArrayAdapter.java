package UIAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.exercise_5.R;

import java.util.ArrayList;
import java.util.List;

import API.Models.IOrder;
import BusinessEntities.Order;
import BusinessEntities.Table;

public class OrderArrayAdapter extends ArrayAdapter<Table> {

    private List<IOrder> orders;
    private Context context;
    private int resource;

    public OrderArrayAdapter(@NonNull Context context, int resource, List<IOrder> orders) {
        super(context, resource);
        if (orders != null) {
            this.orders = orders;
        }
        else {
            this.orders = new ArrayList<>();
            this.orders.add(new Order());
            this.orders.add(new Order());
            this.orders.add(new Order());
        }
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        TextView textViewOrderNumber = (TextView) convertView.findViewById(R.id.text_view_order_number);
        IOrder order = orders.get(position);

        // Set order data:

        textViewOrderNumber.setText("כאן יוצג פריט מספר " + (position + 1));

        return convertView;
    }
}
