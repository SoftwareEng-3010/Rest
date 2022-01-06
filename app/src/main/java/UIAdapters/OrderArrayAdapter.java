package UIAdapters;

import android.content.Context;
import android.util.Log;
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
import BusinessEntities.Item;
import BusinessEntities.Order;
import BusinessEntities.Table;

public class OrderArrayAdapter extends ArrayAdapter<Item> {

    private final String TAG = "OrderArrayAdapter";

    private List<Item> orderItems;
    private Context context;
    private int resource;

    public OrderArrayAdapter(@NonNull Context context, int resource, List<Item> items){
        super(context, resource, items);
        this.orderItems = new ArrayList<>(items);
        this.context = context;
        this.resource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Item item = orderItems.get(position);
        String itemName = item.getName();

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        return convertView;
    }
}
