package UI.DataActivity.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exercise_5.R;

import java.util.ArrayList;
import java.util.List;

import BusinessEntities.Table;
import UI.DataActivity.Controller.DataViewController;

public class CreateTablesFragment extends Fragment {

    private DataViewController viewController;

    private EditText editTextNumberOfTables;
    private Button buttonFinish;

    public CreateTablesFragment(DataViewController viewController) {
        this.viewController = viewController;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_tables, container, false);

        editTextNumberOfTables = (EditText) v.findViewById(R.id.edit_text_table_number);
        buttonFinish = (Button) v.findViewById(R.id.button_finish_branch_edit);

        buttonFinish.setOnClickListener(this::onFinishClicked);
        return v;
    }


    public void onFinishClicked(View v) {
        if (editTextNumberOfTables.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "הכנס את מספר השולחנות בסניפך", Toast.LENGTH_SHORT).show();
        }
        else {
            int numTables = Integer.parseInt(editTextNumberOfTables.getText().toString());

            if (numTables > 0 && numTables < 1000) {
                List<Table> tables = new ArrayList<>();
                for (int i = 0; i < numTables; i++) {
                    tables.add(new Table(i, 4, 0, false, true));
                }
                viewController.onTablesEditFinished(tables);
            }
        }
    }
}