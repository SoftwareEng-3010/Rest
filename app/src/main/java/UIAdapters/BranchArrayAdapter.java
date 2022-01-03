package UIAdapters;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.exercise_5.R;

import java.util.List;

import API.Constants.Constants;
import BusinessEntities.Branch;
import BusinessEntities.Table;
import UI.CustomersUI.BranchViewActivity;
//import UI.SimpleUserUI.BranchViewActivity;

public class BranchArrayAdapter extends ArrayAdapter<Branch>{

    private static final String TAG = "BranchArrayAdapter";
    private Context context;
    private int resource;
    private List<Branch> branches;
    private String restId;
    private EditText editTextTableNumber;

    public BranchArrayAdapter(@NonNull Context context, int resource, List<Branch> branches, String restId) {
        super(context, resource, branches);
        this.context = context;
        this.resource = resource;
        this.branches = branches;
        this.restId = restId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Branch branch = branches.get(position);
        String branchAddress = branch.getAddress().toString();

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        // Lookup view for data population
        Button btnMoveToBranch = convertView.findViewById(R.id.button_select_branch);

        boolean isClicked = (btnMoveToBranch.getText().toString().equals("המשך"));
        if (! isClicked) {
            btnMoveToBranch.setText(branchAddress);
            btnMoveToBranch.setOnClickListener(this::onFirstClick);
        }

        // Return the complete view to render on screen
        return convertView;
    }

    public void onFirstClick(View v) {

        LinearLayout layout = (LinearLayout)v.getParent();

        TextView textView = layout.findViewById(R.id.text_view_select_table_number);
        editTextTableNumber = layout.findViewById(R.id.edit_text_table_number);

        textView.setVisibility(View.VISIBLE);
        editTextTableNumber.setVisibility(View.VISIBLE);

        ((Button) v).setText("המשך");
        v.setOnClickListener(this::onSecondClick);
    }

    public void onSecondClick(View v) {

        if (editTextTableNumber.getText().toString().isEmpty()) {
            Toast.makeText(context, "נא הכנס את מספר השולחן בו תרצה לשבת", Toast.LENGTH_SHORT).show();
            return;
        }

        ListView parentView = (ListView) v.getParent().getParent();
        int branchIndex = parentView.indexOfChild((View) v.getParent());

        int tableNumber = Integer.parseInt(editTextTableNumber.getText().toString());
        Branch branch = branches.get(branchIndex);

        if (branch.getTables() == null) {
            Log.e(TAG, "This branch has no tables!!! (" + branch.getDocId() + ")");
            Toast.makeText(context, "This branch has no tables!!! (" + branch.getDocId() + ")", Toast.LENGTH_SHORT).show();
            return;
        }

        for (Table table : branch.getTables()) {
            if (table.getTableNumber() == tableNumber) {
                moveToBranchViewActivity(branch, tableNumber);
                return;
            }
        }
        Log.e(TAG, "No such table in branch " + branch.getDocId());
        Toast.makeText(context, "No such table number", Toast.LENGTH_SHORT).show();
    }

    private void moveToBranchViewActivity(Branch branch, int tableNumber) {
        Intent moveToBranchViewActivity =
                new Intent(getContext(), BranchViewActivity.class);

        String branchId = branch.getDocId();
        String menuPath = branch.getMenuPath();
        String restId = BranchArrayAdapter.this.restId;

        moveToBranchViewActivity.putExtra(Constants.KEY_RESTAURANT_ID, restId);
        moveToBranchViewActivity.putExtra(Constants.KEY_BRANCH_ID, branchId);
        moveToBranchViewActivity.putExtra(Constants.KEY_TABLE_NUMBER, tableNumber);
        getContext().startActivity(moveToBranchViewActivity);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }
}
