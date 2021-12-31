package UIAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.exercise_5.R;

import java.util.List;

import API.Constants.Constants;
import BusinessEntities.Branch;
import BusinessEntities.Table;
import UI.CustomersUI.BranchViewActivity;

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

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // Get the data item for this position
//        Table table = tables.get(position);
//        String tableIndex = String.valueOf(table.getTableIndex());
//
//        // Check if an existing view is being reused, otherwise inflate the view
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
//        }
//
//        // Lookup view for data population
//        Button moveToMenu = convertView.findViewById(R.id.branchMenuButton);
//        moveToMenu.setText(branchAddress);
//        moveToMenu.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            /**
//             * move to BranchViewActivity
//             */
//            public void onClick(View v) {
//
//                ListView parentView = (ListView) v.getParent().getParent();
//                int branchIndex = parentView.indexOfChild((View) v.getParent());
//                Intent moveToBranchViewActivity =
//                        new Intent(getContext(), BranchViewActivity.class);
//
//                String menuPath = tables.get(branchIndex).getMenuPath();
//                String branchId = table.getDocId();
//
//                moveToBranchViewActivity.putExtra(Constants.KEY_BRANCH_ID, branchId);
//                moveToBranchViewActivity.putExtra(Constants.KEY_MENU_PATH, menuPath);
//                getContext().startActivity(moveToBranchViewActivity);
//            }
//        });
//
//        // Return the completed view to render on screen
//        return convertView;
//    }

}
