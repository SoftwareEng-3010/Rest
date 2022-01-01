package UIAdapters;


import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.exercise_5.R;

import java.util.List;

import API.Constants.Constants;
import BusinessEntities.Branch;
import UI.CustomersUI.ActionListener;
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
        Button moveToBranchView = convertView.findViewById(R.id.button_select_branch);

        boolean isClicked = (moveToBranchView.getText().toString().equals("המשך"));
        if (! isClicked) {
            moveToBranchView.setText(branchAddress);
            moveToBranchView.setOnClickListener(this::onFirstClick);
        }

        convertView.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        Toast.makeText(context, "Focus: " + b, Toast.LENGTH_SHORT).show();
                    }
                }
        );
//        moveToBranchView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            /**
//             * move to BranchViewActivity
//             */
//            public void onClick(View v) {
//
////                ListView parentView = (ListView) v.getParent().getParent();
////                int branchIndex = parentView.indexOfChild((View) v.getParent());
//
//                Branch branch = branches.get(position);
//
//
//
////                moveToBranchViewActivity(branchIndex);
//            }
//        });

        // Return the completed view to render on screen
        return convertView;
    }

    public void onFirstClick(View v) {
        Toast.makeText(getContext(), "Clicked first time!", Toast.LENGTH_SHORT).show();

        LinearLayout layout = (LinearLayout)v.getParent();

        TextView textView = layout.findViewById(R.id.text_view_select_table_number);
        editTextTableNumber = layout.findViewById(R.id.edit_text_table_number);

        editTextTableNumber.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        Log.e(TAG, "beforeTextChanged: " + i + ", " + i1 + ", " + i2);
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        Log.e(TAG, "onTextChanged: " + i + ", " + i1 + ", " + i2);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        Log.e(TAG, "Editable: " + editable.toString());

                    }
                }
        );

        textView.setVisibility(View.VISIBLE);
        editTextTableNumber.setVisibility(View.VISIBLE);

        ((Button) v).setText("המשך");
        v.setOnClickListener(this::onSecondClick);
        ((View)v.getParent()).setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (b) {
                            Toast.makeText(getContext(), "Focus is " + b, Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getContext(), "Focus is " + b, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    public void onSecondClick(View v) {
        Toast.makeText(getContext(), "Clicked second time!", Toast.LENGTH_SHORT).show();

        ListView parentView = (ListView) v.getParent().getParent();
        int branchIndex = parentView.indexOfChild((View) v.getParent());
        moveToBranchViewActivity(branchIndex);
    }

    private void moveToBranchViewActivity(int branchIndex) {
        Intent moveToBranchViewActivity =
                new Intent(getContext(), BranchViewActivity.class);

//                Map<String, Object> data = new HashMap<>();

        Branch branch = branches.get(branchIndex);

        String menuPath = branches.get(branchIndex).getMenuPath();
        String branchId = branch.getDocId();
        String restId = BranchArrayAdapter.this.restId;
        int tableNumber = Integer.parseInt(editTextTableNumber.getText().toString());

//                BranchArrayAdapter.this.listener.onAction(data);

        moveToBranchViewActivity.putExtra(Constants.KEY_RESTAURANT_ID, restId);
        moveToBranchViewActivity.putExtra(Constants.KEY_BRANCH_ID, branchId);
        moveToBranchViewActivity.putExtra(Constants.KEY_MENU_PATH, menuPath);
        moveToBranchViewActivity.putExtra(Constants.KEY_TABLE_NUMBER, tableNumber);
        getContext().startActivity(moveToBranchViewActivity);
    }
}
