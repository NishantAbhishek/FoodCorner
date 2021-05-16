package com.example.foodcorner.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodcorner.Model.BookingItem;
import com.example.foodcorner.Model.NormalResponse;
import com.example.foodcorner.Repository.BookedRestroRepo;

public class BookedRestroViewModel extends ViewModel{

    private static final String TAG = "BookedRestroViewModel";
    private BookedRestroRepo bookedRestroRepo;

    public BookedRestroViewModel(){
        bookedRestroRepo = BookedRestroRepo.getInstance();
    }

    public LiveData<BookingItem> getBooked(int userId){
        bookedRestroRepo.getBookedRestaurant(userId);
        return bookedRestroRepo.getBookedLive();
    }

    public LiveData<NormalResponse> removeBooking(int userId,int bookingId){
        return bookedRestroRepo.removeBooking(userId,bookingId);
    }


}
