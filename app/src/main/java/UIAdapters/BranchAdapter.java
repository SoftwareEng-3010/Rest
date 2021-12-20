package UIAdapters;


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
import com.google.firebase.firestore.DocumentReference;

import java.util.List;

import BusinessEntities.Branch;
import UI.BranchViewActivity;
//import UI.BranchViewActivity;

public class BranchAdapter extends ArrayAdapter<Branch> {

    private static final String TAG = "BranchAdapter";
    private Context context;
    private int resource;
    private List<Branch> branches;

    public BranchAdapter(@NonNull Context context, int resource, List<Branch> branches) {
        super(context, resource, branches);
        this.branches = branches;
        this.context = context;
        this.resource = resource;
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
        Button moveToMenu = convertView.findViewById(R.id.branchMenuButton);
        moveToMenu.setText(branchAddress);
        moveToMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * move to BranchViewActivity
             */
            public void onClick(View v) {

                ListView parentView = (ListView) v.getParent().getParent();
                int branchIndex = parentView.indexOfChild((View) v.getParent());
                Intent moveToBranchViewActivity =
                        new Intent(getContext(), BranchViewActivity.class);

                String menuPath = branches.get(branchIndex).getMenuPath();
                String branchId = branch.getDocId();

                moveToBranchViewActivity.putExtra("branch_id", branchId);
                moveToBranchViewActivity.putExtra("menu_path", menuPath);
                getContext().startActivity(moveToBranchViewActivity);
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }

}
