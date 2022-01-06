package UIAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exercise_5.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import API.Models.IOrder;

public class OrdersRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final String TAG = "OrdersViewAdapter";
    private Context context;
    private List<IOrder> orders;

    public OrdersRecyclerViewAdapter(Context context){
        this.context = context;
        this.orders = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order, parent, false);
        Log.e(TAG, "layout_order ??");
        return new RecyclerView.ViewHolder(v) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        IOrder order = orders.get(position);

        TextView tableNum = (TextView) holder.itemView.findViewById(R.id.table_number);

        tableNum.setText(order.getTable().getTableNumber());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void addOrder(IOrder order){
        orders.add(order);
    }
}
