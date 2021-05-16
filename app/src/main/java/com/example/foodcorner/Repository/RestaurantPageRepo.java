package com.example.foodcorner.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodcorner.Model.NormalResponse;
import com.example.foodcorner.Model.RestaurantList;
import com.example.foodcorner.Network.ApiService;
import com.example.foodcorner.Network.RetrofitInstance;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantPageRepo {
    private MutableLiveData<RestaurantList> restaurantById;
    private MutableLiveData<NormalResponse> bookResponse;
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
        bookResponse = new MutableLiveData<NormalResponse>();
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

    public LiveData<NormalResponse> bookRestaurant(JsonObject bookBody){
        ApiService apiService = RetrofitInstance.getRetrofit().create(ApiService.class);
        Call<NormalResponse> call = apiService.bookRestaurant(bookBody);
        Log.e("Auth:-","Sending");
        call.enqueue(new Callback<NormalResponse>() {
            @Override
            public void onResponse(Call<NormalResponse> call, Response<NormalResponse> response) {
                if(response.isSuccessful()){
                    bookResponse.postValue(response.body());
                }else{
                    Log.e("bookRestaurant:- not succc    :---",response.message());
                    bookResponse.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<NormalResponse> call, Throwable t) {
                bookResponse.postValue(null);
                Log.e("bookRestaurant",t.getMessage());
            }
        });
        return bookResponse;
    }

}
