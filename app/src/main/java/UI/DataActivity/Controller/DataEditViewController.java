package UI.DataActivity.Controller;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.PropertyName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import API.Constants.Constants;
import API.Database.Database;
import API.Database.DatabaseRequestCallback;
import API.Database.OnDataSentToDB;
import API.Models.IBranchManagerUser;
import BusinessEntities.Address;
import BusinessEntities.Branch;
import BusinessEntities.BranchManager;
import BusinessEntities.Item;
import BusinessEntities.Menu;
import BusinessEntities.Restaurant;
import BusinessEntities.Table;
import DataAccessLayer.RestDB;
import UI.DataActivity.View.DataEditView;

public class DataEditViewController implements DataViewController{

    private final String TAG = "EditViewController";

    private DataEditView view;

    private String restaurantName;

    private Address branchAddress;
    private boolean isKosher;
    private List<Item> itemList;
    private List<Table> tables;

    public DataEditViewController(DataEditView dataEditView) {
        this.view = dataEditView;
    }

    @Override
    public void onRestaurantEditFinished(String restaurantName) {

        // if (database already contains a restaurant named `restaurantName`...) {}

        if (restaurantName != null) {
            this.restaurantName = restaurantName;
            view.onRestaurantEditFinished(true, restaurantName);
        }
        else {
            view.onRestaurantEditFinished(false, null);
        }
        // Should change `rest_name` with some constant
//        dataBundle.putString("rest_name", restaurantName);
    }

    @Override
    public void onBranchEditFinished(Address address, boolean isKosher) {
        if (address == null)
            return;

        this.branchAddress = address;
        this.isKosher = isKosher;
        view.onBranchEditFinished(true, address, isKosher);
    }

    @Override
    public void onMenuEditFinished(List<Item> menuItems) {
        if (menuItems.size() > 0) {
            this.itemList = new ArrayList<>(menuItems);
            view.onMenuEditFinished(true, menuItems);
        }
        else {
            view.onMenuEditFinished(false, menuItems);
        }
    }

    @Override
    public void onTablesEditFinished(List<Table> tables) {
        if (tables.size() > 0) {
            this.tables = tables;
            view.onTablesEditFinished(true, tables);
            onDataEditFinished();
        }
        else {
            view.onTablesEditFinished(false, tables);
        }
    }

    public void onDataEditFinished() {

        RestDB db = RestDB.getInstance();
        db.addRestaurant(
                new Restaurant(restaurantName),
                new OnDataSentToDB() {
                    @Override
                    public void onObjectWrittenToDB(boolean isTaskSuccessful) {
                        if (isTaskSuccessful) {
                            Log.e(TAG, "Restaurant created successfully");
                        }
                    }
                },
                new DatabaseRequestCallback() {
                    @Override
                    public void onObjectReturnedFromDB(@Nullable Object obj) {
                        if (obj == null) {
                            Log.e(TAG, "Object came back null from DB!");
                            return;
                        }

                        String restId = (String) obj;

                        // Create and add the menu under restaurant `restId`.
                        Menu menu = new Menu(itemList);

                        db.addMenu(restId, menu,
                                new DatabaseRequestCallback() {
                                    @Override
                                    public void onObjectReturnedFromDB(@Nullable Object obj) {
                                        if (obj == null) {
                                            Log.e(TAG, "menu_path came back NULL from DB!");
                                            return;
                                        }

                                        String menuPath = (String) obj;

                                        Branch branch = new Branch(
                                                branchAddress, isKosher, menuPath, tables);

                                        db.addBranch(restId, branch,
                                                new OnDataSentToDB() {
                                                    @Override
                                                    public void onObjectWrittenToDB(boolean isTaskSuccessful) {

                                                    }
                                                },
                                                new DatabaseRequestCallback() {
                                                    @Override
                                                    public void onObjectReturnedFromDB(@Nullable Object obj) {
                                                        if (obj == null) {
                                                            Log.e(TAG, "Branch came back null for some reason");
                                                        }
                                                        String branchId = (String) obj;

                                                        db.getUser(
                                                                FirebaseAuth.getInstance().getUid(),
                                                                new DatabaseRequestCallback() {
                                                                    @Override
                                                                    public void onObjectReturnedFromDB(@Nullable Object obj) {
                                                                        BranchManager user = (BranchManager) obj;
                                                                        Map<String, Object> newUser = new HashMap<>();
                                                                        newUser.put("id", user.getUid());
                                                                        newUser.put("type", user.getType());
                                                                        newUser.put("email", user.getEmail());
                                                                        newUser.put("branch_id", branchId);
                                                                        newUser.put("rest_id", restId);

                                                                        db.setUser(
                                                                                newUser,
                                                                                Constants.USER_TYPE_BRANCH_MANAGER,
                                                                                new OnDataSentToDB() {
                                                                                    @Override
                                                                                    public void onObjectWrittenToDB(boolean isTaskSuccessful) {
                                                                                        if (isTaskSuccessful) {
                                                                                            view.onDataEditFinish(restId, branchId);
                                                                                        }
                                                                                    }
                                                                                });
                                                                    }
                                                                }
                                                        );
                                                    }
                                                });

//                                        db.addBranch(restId, branch);

//                                        List<Branch> branches = new ArrayList<>();
//                                        branches.add(branch);
//
//                                        Restaurant restaurant = new Restaurant(restaurantName, branches);
//                                        db.setRestaurant(
//                                                restId, restaurant,
//                                            new OnDataSentToDB() {
//                                                @Override
//                                                public void onObjectWrittenToDB(boolean isTaskSuccessful) {
//                                                    if (isTaskSuccessful) {
//                                                        Log.e(TAG, "Successfully written restaurant into DB!");
//                                                        view.onDataEditFinish(restaurant, branch);
//                                                    }
//                                                    else {
//                                                        Log.e(TAG, "Failed to write restaurant into DB!");
//                                                    }
//                                                }
//                                            });
                                    }
                                });
                    }
                });
    }
}
