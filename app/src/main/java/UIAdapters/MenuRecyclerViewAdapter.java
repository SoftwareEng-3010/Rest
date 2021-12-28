package UIAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exercise_5.R;

import java.util.ArrayList;
import java.util.List;

import API.BusinessEntitiesInterface.IOrder;
import BusinessEntities.Item;

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<Item> items;
    private List<Item> selectedItems;

    public MenuRecyclerViewAdapter(Context context, List<Item> menuItems) {
        this.context = context;
        this.items = new ArrayList<>(menuItems);
        this.selectedItems = new ArrayList<>();
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
        TextView itemNameTV = (TextView) holder.itemView.findViewById(R.id.menu_item_name_TV);
        TextView itemDescriptionTV = (TextView) holder.itemView.findViewById(R.id.menu_item_description_TV);
        TextView itemPriceTV = (TextView) holder.itemView.findViewById(R.id.menu_item_price_TV);

        itemNameTV.setText(item.getName());
        itemDescriptionTV.setText(item.getDescription());
        itemPriceTV.setText(Integer.toString((int)item.getPrice()) + "₪");

        Button buttonAddRemove = (Button) holder.itemView.findViewById(R.id.btn_add_to_order);
        buttonAddRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItems.contains(item)) {
                    selectedItems.remove(item);
                    buttonAddRemove.setText("ADD TO ORDER");
                }
                else {
                    selectedItems.add(item);
                    buttonAddRemove.setText("REMOVE FROM ORDER");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Item item) {
        if (item == null) return;
        this.items.add(item);
    }

    public List<Item> getSelectedItems() {
        return selectedItems;
    }
}
