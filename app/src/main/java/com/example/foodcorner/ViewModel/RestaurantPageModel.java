package com.example.foodcorner.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodcorner.Model.RestaurantList;
import com.example.foodcorner.Repository.RestaurantPageRepo;

public class RestaurantPageModel extends ViewModel {
    private static final String TAG = "RestaurantPageModel";
    private RestaurantPageRepo instance;

    public RestaurantPageModel(){
        instance = RestaurantPageRepo.getInstance();
    }

    public LiveData<RestaurantList> getRestaurantById(int id){
        return instance.getRestaurantById(id);
    }

    public void setNull(){
        instance.setNull();
    }

}
