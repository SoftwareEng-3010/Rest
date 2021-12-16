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

import java.util.List;

import UI.BranchViewActivity;

public class BranchAdapter extends ArrayAdapter<BranchSmallViewModel> {

    private static final String TAG = "BranchAdapter";
    private Context context;
    private int resource;
    private List<BranchSmallViewModel> branches;

    public BranchAdapter(@NonNull Context context, int resource, List<BranchSmallViewModel> branches) {
        super(context, resource, branches);
        this.branches = branches;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        BranchSmallViewModel branch = branches.get(position);
        String branchAddress = branch.getAddress().toString();

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        // Lookup view for data population
        Button moveToMenu = convertView.findViewById(R.id.branchMenuButton);
        moveToMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * move to BranchViewActivity
             */
            public void onClick(View v) {

                ListView parentView = (ListView) v.getParent().getParent().getParent();
                int restIndex = parentView.indexOfChild((View) v.getParent().getParent());
                int branchIndex = parentView.indexOfChild((View) v.getParent().getParent());
                Intent moveToSingleBranchActivity =
                        new Intent(getContext(), BranchViewActivity.class);
                moveToSingleBranchActivity.putExtra("restaurant_index", restIndex);
                moveToSingleBranchActivity.putExtra("branch_index", branchIndex);
                getContext().startActivity(moveToSingleBranchActivity);
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }

}
