package com.example.foodcorner.Repository;
import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.foodcorner.Dao.RestaurantDao;
import com.example.foodcorner.Restaurant;
import com.example.foodcorner.RestaurantDatabase;

import java.util.List;

public class  RestaurantDaRepo{
    private RestaurantDao restaurantDao;
    private LiveData<List<Restaurant>> restaurantListLiveData;

    public RestaurantDaRepo(Application application){
        RestaurantDatabase database = RestaurantDatabase.getInstance(application);
        restaurantDao = database.restaurantDao();
        restaurantListLiveData = restaurantDao.getAllRestaurant();
    }

    public LiveData<List<Restaurant>> getAllRestaurants(){
        return restaurantListLiveData;
    }

    public void deleteAllRestaurant(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                restaurantDao.deleteAllRestaurant();
            }
        }).start();
    }

    public void insertRestaurant(Restaurant restaurant){
        new Thread(new Runnable() {
            @Override
            public void run() {
                restaurantDao.insert(restaurant);
            }
        }).start();
    }
}
