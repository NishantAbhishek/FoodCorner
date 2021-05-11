package com.example.foodcorner.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodcorner.Repository.RestaurantDaRepo;
import com.example.foodcorner.Restaurant;

import java.util.List;

public class RestaurantDbViewModel extends ViewModel{
    private static final String TAG = "RestaurantDbViewModel";
    private MutableLiveData<List<Restaurant>> restaurants;
    private RestaurantDaRepo restaurantDaRepo;

    public RestaurantDbViewModel(@NonNull Application application){
        restaurantDaRepo = new RestaurantDaRepo(application);
    }

    public void insertRestaurant(Restaurant restaurant){
        restaurantDaRepo.insertRestaurant(restaurant);
    }

    public void deleteRestaurant(Restaurant restaurant){
        restaurantDaRepo.deleteAllRestaurant();
    }

}
