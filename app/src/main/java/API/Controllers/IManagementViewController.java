package API.Controllers;

import BusinessEntities.Table;

public interface IManagementViewController {

    void onHomeButtonClicked();
    void onServiceButtonClicked();
    void onTableItemClicked(Table table);
    void onKitchenButtonClicked();
}
