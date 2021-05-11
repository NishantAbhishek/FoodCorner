package com.example.foodcorner.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodcorner.Model.RestaurantList;
import com.example.foodcorner.Network.ApiService;
import com.example.foodcorner.Network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantPageRepo {
    private MutableLiveData<RestaurantList> restaurantById;
    public static RestaurantPageRepo instance;
    public static RestaurantPageRepo getInstance(){
        if(instance==null){
            instance = new RestaurantPageRepo();
        }
        return instance;
    }

    public void setNull(){
        instance = null;
    }


    private RestaurantPageRepo(){
        restaurantById = new MutableLiveData<RestaurantList>();
    }

    public LiveData<RestaurantList> getRestaurantById(int id){
        ApiService apiService = RetrofitInstance.getRetrofit().create(ApiService.class);
        Call<RestaurantList>  call = apiService.getRestaurantById(id);
        call.enqueue(new Callback<RestaurantList>() {
            @Override
            public void onResponse(Call<RestaurantList> call, Response<RestaurantList> response) {
                if(response.isSuccessful()){
                    restaurantById.postValue(response.body());
                }else{
                    restaurantById.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<RestaurantList> call, Throwable t) {
                restaurantById.postValue(null);
            }
        });

        return restaurantById;

    }



}
