package UIAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.exercise_5.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import BusinessEntities.Item;

public class OrderArrayAdapter extends ArrayAdapter<Item> {

    private final String TAG = "OrderArrayAdapter";

    private List<Item> orderItems;
    private Context context;
    private int resource;
    private View view;

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
            view = convertView.getRootView();
        }

        TextView itemNameTV = (TextView) view.findViewById(R.id.order_item_name);
        itemNameTV.setText(itemName);
        return convertView;
    }
}
