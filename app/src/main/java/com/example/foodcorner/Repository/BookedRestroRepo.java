package com.example.foodcorner.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodcorner.Model.BookingItem;
import com.example.foodcorner.Model.NormalResponse;
import com.example.foodcorner.Network.ApiService;
import com.example.foodcorner.Network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookedRestroRepo {
    private MutableLiveData<BookingItem> bookedList;
    private MutableLiveData<NormalResponse> removeBooking;
    public static BookedRestroRepo instance;

    public static BookedRestroRepo getInstance(){
        if(instance==null){
            instance = new BookedRestroRepo();
        }
        return instance;
    }

    private BookedRestroRepo(){
        bookedList = new MutableLiveData<>();
        removeBooking = new MutableLiveData<>();
    }

    public void getBookedRestaurant(int userId){
        ApiService apiService = RetrofitInstance.getRetrofit().create(ApiService.class);
        apiService.getBookedRestaurant(userId).enqueue(new Callback<BookingItem>() {
            @Override
            public void onResponse(Call<BookingItem> call, Response<BookingItem> response){
                if(response.isSuccessful()){
                    bookedList.postValue(response.body());
                }else{
                    bookedList.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<BookingItem> call, Throwable t) {
                bookedList.postValue(null);
            }
        });
    }

    public LiveData<BookingItem> getBookedLive(){
        return bookedList;
    }

    public LiveData<NormalResponse> removeBooking(int userId,int bookingId){
        ApiService apiService = RetrofitInstance.getRetrofit().create(ApiService.class);
        apiService.removeBooking(userId,bookingId).enqueue(new Callback<NormalResponse>() {
            @Override
            public void onResponse(Call<NormalResponse> call, Response<NormalResponse> response) {
                if(response.isSuccessful()){
                    removeBooking.postValue(response.body());
                }else {
                    removeBooking.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<NormalResponse> call, Throwable t) {
                removeBooking.postValue(null);
            }
        });

        return removeBooking;
    }
}
