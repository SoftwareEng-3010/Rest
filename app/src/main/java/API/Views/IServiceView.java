package API.Views;

import androidx.annotation.NonNull;

import java.util.List;

import BusinessEntities.Table;

public interface IServiceView {

    public void loadTableDetailsFragment();
    public void setupTableGridView(@NonNull List<Table> tables);
}
