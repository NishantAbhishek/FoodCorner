package com.example.foodcorner.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodcorner.Model.BookingItem;
import com.example.foodcorner.Model.NormalResponse;
import com.google.gson.JsonObject;

public class BookingViewModel extends ViewModel{
    private static final String TAG = BookingViewModel.class.toString();
    private MutableLiveData<BookingItem> bookingItems;
    private MutableLiveData<NormalResponse> bookingMessage;
    public BookingViewModel(){
        bookingItems = new MutableLiveData<>();
        bookingMessage = new MutableLiveData<>();
    }

    public MutableLiveData<BookingItem> getBookingListObserver(){
        return bookingItems;
    }

    public void bookingListApiCall(String authData){

    }

    public void bookApiCall(JsonObject jsonObject){

    }


}
