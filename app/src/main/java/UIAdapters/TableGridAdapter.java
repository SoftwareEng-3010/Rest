package UIAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.exercise_5.R;

import java.util.ArrayList;
import java.util.List;

import BusinessEntities.Table;

public class TableGridAdapter extends BaseAdapter {

    private Context context;
    private List<Table> tables;
    private LayoutInflater inflater;

    public TableGridAdapter(Context context, List<Table> tables) {
        this.context = context;
        this.tables = new ArrayList<>(tables);
    }

    @Override
    public int getCount() {
        return tables.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_table_item, null);
        }

        TextView textView = convertView.findViewById(R.id.table_number);
        textView.setText(String.valueOf(tables.get(position).getTableNumber()));

        return convertView;
    }
}
