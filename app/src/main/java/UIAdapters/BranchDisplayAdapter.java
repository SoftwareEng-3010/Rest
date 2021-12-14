package UIAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.exercise_5.R;

import java.util.List;

import BusinessEntities.Branch;
import BusinessEntities.Item;

public class BranchDisplayAdapter extends ArrayAdapter<Item> {

    private static final String TAG = "BranchDisplayAdapter";
    private Context context;
    private int resource;
    private List<Item> menu;

    public BranchDisplayAdapter(@NonNull Context context, int resource, List<Item> menu) {
        super(context, resource, menu);
        this.menu = menu;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Item item = menu.get(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }
        // Lookup view for data population
        // TODO: 12/15/2021 can't convert ListView to TextView
//        TextView bMenu = convertView.findViewById(R.id.MenuList);
//        TextView bAddress = convertView.findViewById(R.id.branchMenuTextView);
//        TextView bIsKosher = convertView.findViewById(R.id.branchKosherTextView);

        // Populate the data into the template view using the data object
//        bAddress.setText(branch.getMenu().toString());
//        bMenu.setText(menu.toString());

        // Return the completed view to render on screen
        return convertView;
    }
}
