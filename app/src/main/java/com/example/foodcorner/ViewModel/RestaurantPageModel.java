package com.example.foodcorner.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodcorner.Model.NormalResponse;
import com.example.foodcorner.Model.RestaurantList;
import com.example.foodcorner.Repository.RestaurantPageRepo;
import com.google.gson.JsonObject;

public class RestaurantPageModel extends ViewModel {
    private static final String TAG = "RestaurantPageModel";
    private RestaurantPageRepo instance;

    public RestaurantPageModel(){
        instance = RestaurantPageRepo.getInstance();
    }

    public LiveData<RestaurantList> getRestaurantById(int id){
        return instance.getRestaurantById(id);
    }

    public LiveData<NormalResponse> bookRestaurant(JsonObject bookBody){
        return instance.bookRestaurant(bookBody);
    }

    public void setNull(){
        instance.setNull();
    }
}
