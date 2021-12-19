package ViewModels;

import androidx.lifecycle.LiveData;

import java.util.List;

import BusinessEntities.Restaurant;

public class RestaurantViewModel {

    private LiveData<Restaurant> restaurant;

    public LiveData<Restaurant> getRestaurant() {
        return this.restaurant;
    }
}
