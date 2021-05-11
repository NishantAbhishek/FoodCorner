package com.example.foodcorner.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodcorner.Model.RestaurantList;
import com.example.foodcorner.Repository.RestaurantRepository;


public class RestaurantViewModel extends ViewModel{
    //all the business logic for Home Fragment
    private static final String TAG = "RestaurantViewModel";
    private RestaurantRepository restaurantRepository;

    public RestaurantViewModel(){
        restaurantRepository = RestaurantRepository.getInstance();
    }
    public LiveData<RestaurantList> getRestaurantObserver(int page, int limit){
        return restaurantRepository.getRestaurantList(page,limit);
    }
    public LiveData<RestaurantList> getHPriceyResObserver(int page,int limit){
        return restaurantRepository.getHPriceyResList(page,limit);
    }
    public LiveData<RestaurantList> getLPriceyResObserver(int page,int limit){
        return restaurantRepository.getLPriceyResList(page,limit);
    }
    public LiveData<RestaurantList> getHRatedResObserver(int page,int limit){
        return restaurantRepository.getHighTRated(page,limit);
    }


}
