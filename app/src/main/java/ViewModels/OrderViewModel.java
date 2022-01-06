package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import API.Models.IOrder;

public class OrderViewModel extends ViewModel {
    private LiveData<List<IOrder>> orders;

    public LiveData<List<IOrder>> getOrders() {
        return orders;
    }
}
