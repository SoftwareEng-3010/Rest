package API.Views;

import androidx.annotation.NonNull;

import BusinessEntities.Table;

public interface ITableDetailsView {
    public void loadTableDetails(@NonNull Table table);
}
