package UIAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

import BusinessEntities.Table;

public class TableArrayAdapter extends ArrayAdapter<Table> {

    private static final String TAG = "TableArrayAdapter";
    private Context context;
    private int resource;
    private List<Table> tables;

    public TableArrayAdapter(@NonNull Context context, int resource, List<Table> tables) {
        super(context, resource, tables);
        this.tables = tables;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Table table = tables.get(position);
        String tableIndex = String.valueOf(table.getTableNumber());

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        // Return the completed view to render on screen
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e(TAG, "table clicked");
            }
        });

        return convertView;
    }

}
