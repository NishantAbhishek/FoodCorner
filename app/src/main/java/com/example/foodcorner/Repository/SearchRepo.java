package com.example.foodcorner.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodcorner.Model.RestaurantList;
import com.example.foodcorner.Network.ApiService;
import com.example.foodcorner.Network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRepo {
    private static SearchRepo instance;
    private MutableLiveData<List<RestaurantList.data>> restaurantList;
    public static SearchRepo getInstance(){
        if(instance == null){
            instance = new SearchRepo();
        }
        return instance;
    }

    private SearchRepo(){
        restaurantList = new MutableLiveData<>();
    }

    public void getLiveRestaurant(String param){
        ApiService apiService = RetrofitInstance.getRetrofit().create(ApiService.class);
        apiService.searchRestaurant(param).enqueue(new Callback<RestaurantList>() {
            @Override
            public void onResponse(Call<RestaurantList> call, Response<RestaurantList> response) {
                if(response.isSuccessful()){
                    restaurantList.postValue(response.body().getData());
                }else{
                    restaurantList.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<RestaurantList> call, Throwable t) {
                restaurantList.postValue(null);
            }
        });
    }

    public LiveData<List<RestaurantList.data>> getLiveList(){
        return restaurantList;
    }

}
