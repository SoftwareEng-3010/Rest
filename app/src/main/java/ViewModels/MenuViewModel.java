package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import BusinessEntities.Item;

public class MenuViewModel extends ViewModel {
    private LiveData<List<Item>> items;
    // LiveData<Menu> menu; // ??

    public LiveData<List<Item>> getItems() {
        return this.items;
    }
}