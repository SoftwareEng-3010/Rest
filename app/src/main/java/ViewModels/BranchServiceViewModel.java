package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import BusinessEntities.Table;

public class BranchServiceViewModel extends ViewModel {

    private LiveData<List<Table>> tables;

    public LiveData<List<Table>> getTables() {
        return this.tables;
    }
}
