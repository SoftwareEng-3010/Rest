package UIAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exercise_5.R;

import java.util.ArrayList;
import java.util.List;

import BusinessEntities.Item;

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<Item> items;

    public MenuRecyclerViewAdapter(Context context, List<Item> menuItems) {
        this.context = context;
        this.items = new ArrayList<>(menuItems);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_menu_list_item, parent, false);
        return new RecyclerView.ViewHolder(v) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Item item = items.get(position);
//        holder.myTextView.setText(animal);
        TextView itemNameTV = (TextView) holder.itemView.findViewById(R.id.menu_item_name_TV);
        TextView itemPriceTV = (TextView) holder.itemView.findViewById(R.id.menu_item_price_TV);
        TextView itemIsInStockTV = (TextView) holder.itemView.findViewById(R.id.menu_item_inStock_TV);

        itemNameTV.setText(item.getName());
        itemPriceTV.setText("" + item.getPrice());
        itemIsInStockTV.setText("" + item.isInStock());

        // TODO: add OnClickListener to button
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
