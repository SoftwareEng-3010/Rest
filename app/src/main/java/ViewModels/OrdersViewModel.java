package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import API.Constants.Constants;
import API.Models.IOrder;
import API.Models.IPrinter;
import BusinessEntities.Printer;
import DataAccessLayer.RestDB;

public class OrdersViewModel extends ViewModel {
    private LiveData<List<IOrder>> orders;

    public LiveData<List<IOrder>> getOrders() {

        IPrinter printer = new Printer(Constants.USER_TYPE_KITCHEN);

        return printer;
    }
}
