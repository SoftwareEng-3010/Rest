//package UIAdapters;
//
//import android.content.Context;
//import android.content.Intent;
//import android.database.DataSetObserver;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Adapter;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//
//import androidx.annotation.NonNull;
//
//import com.example.exercise_5.R;
//
//import java.util.List;
//
//import BusinessEntities.Branch;
//import BusinessEntities.Item;
//import BusinessEntities.Restaurant;
//import BusinessEntities.Table;
//import UI.BranchViewActivity;
//
//public class BranchViewAdapter extends Adapter<Branch> {
//
//    private static final String TAG = "BranchViewAdapter";
//    private Context context;
//    private int resource;
//    private Branch branch;
//
//    public BranchViewAdapter(Context context, int resource, Branch branch) {
//        this.context = context;
//        this.resource = resource;
//        this.branch = branch;
//    }
//
//
//    @Override
//    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
//
//    }
//
//    @Override
//    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
//
//    }
//
//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return false;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        return null;
//    }
//
//    @Override
//    public int getItemViewType(int i) {
//        return 0;
//    }
//
//    @Override
//    public int getViewTypeCount() {
//        return 0;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return false;
//    }
//}
