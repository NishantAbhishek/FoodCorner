package com.example.foodcorner.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodcorner.Model.RestaurantList;
import com.example.foodcorner.Network.ApiService;
import com.example.foodcorner.Network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantRepository {
    public static RestaurantRepository instance;
    private MutableLiveData<RestaurantList> restaurantList;
    private MutableLiveData<RestaurantList> restaurantLowPrice;
    private MutableLiveData<RestaurantList> restaurantHighPrice;
    private MutableLiveData<RestaurantList> restaurantHighRated;


    //create a singleton class
    public static RestaurantRepository getInstance(){
        if(instance == null){
            instance = new RestaurantRepository();
        }
        return instance;
    }

    //create the object once for all the MutableLiveData
    private RestaurantRepository(){
        restaurantList = new MutableLiveData<RestaurantList>();
        restaurantLowPrice = new MutableLiveData<RestaurantList>();
        restaurantHighPrice = new MutableLiveData<RestaurantList>();
        restaurantHighRated = new MutableLiveData<RestaurantList>();
    }

    //get all the Restaurant List
    public LiveData<RestaurantList>  getRestaurantList(int page,int limit){
        ApiService apiService = RetrofitInstance.getRetrofit().create(ApiService.class);
        Call<RestaurantList> call = apiService.getRestaurant(page,limit);
        call.enqueue(new Callback<RestaurantList>() {
            @Override
            public void onResponse(Call<RestaurantList> call, Response<RestaurantList> response) {
                Log.e("Url ",response.toString());
                if(response.isSuccessful()){
                    restaurantList.postValue(response.body());
                }else{
                    Log.e("--","onResposne Errr "+response.errorBody());
                    restaurantList.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<RestaurantList> call, Throwable t) {
                restaurantList.postValue(null);
                Log.e("--","onResponse Err"+t.getMessage());
            }
        });
        return restaurantList;
    }

    //get all the high pricey Restaurant
    public LiveData<RestaurantList> getHPriceyResList(int page,int limit){
        ApiService apiService = RetrofitInstance.getRetrofit().create(ApiService.class);
        Call<RestaurantList> call = apiService.getHighPriceRestaurant(page,limit);
        call.enqueue(new Callback<RestaurantList>() {
            @Override
            public void onResponse(Call<RestaurantList> call, Response<RestaurantList> response) {
                if(response.isSuccessful()){
                    restaurantHighPrice.postValue(response.body());
                }else{
                    restaurantHighPrice.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<RestaurantList> call, Throwable t) {
                restaurantHighPrice.postValue(null);
            }
        });

        return restaurantHighPrice;
    }

    //get all the low pricey Restaurant
    public LiveData<RestaurantList> getLPriceyResList(int page,int limit){
        ApiService apiService = RetrofitInstance.getRetrofit().create(ApiService.class);
        Call<RestaurantList> call = apiService.getLowPriceRestaurant(page,limit);
        call.enqueue(new Callback<RestaurantList>() {
            @Override
            public void onResponse(Call<RestaurantList> call, Response<RestaurantList> response) {
                if(response.isSuccessful()){
                    restaurantLowPrice.postValue(response.body());
                }else{
                    restaurantLowPrice.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<RestaurantList> call, Throwable t) {
                restaurantLowPrice.postValue(null);
            }
        });
        return restaurantLowPrice;
    }

    //get all the high Rated Restaurant
    public LiveData<RestaurantList> getHighTRated(int page,int limit){
        ApiService apiService = RetrofitInstance.getRetrofit().create(ApiService.class);
        Call<RestaurantList> call = apiService.getTopRatedRestaurant(page,limit);
        call.enqueue(new Callback<RestaurantList>() {
            @Override
            public void onResponse(Call<RestaurantList> call, Response<RestaurantList> response) {
                if(response.isSuccessful()){
                    restaurantHighRated.postValue(response.body());
                }else{
                    restaurantHighRated.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<RestaurantList> call, Throwable t) {
                restaurantHighRated.postValue(null);
            }
        });
        return restaurantHighRated;
    }

}
