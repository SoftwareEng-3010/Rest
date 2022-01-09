package API.Controllers;

import androidx.annotation.Nullable;

import java.util.List;

import API.SwipeGestureListener;
import BusinessEntities.Bill;
import BusinessEntities.Table;

public interface IServiceViewController extends SwipeGestureListener {

    public void onBillCloseBtnClicked(@Nullable Bill bill);
    public void onTableItemClicked(Table table);
    public List<Table> getTables();
}
