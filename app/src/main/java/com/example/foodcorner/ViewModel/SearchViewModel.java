package com.example.foodcorner.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodcorner.Model.RestaurantList;
import com.example.foodcorner.Repository.SearchRepo;

import java.util.List;

public class SearchViewModel extends ViewModel{
    private SearchRepo repo;
    public SearchViewModel(){
        if(repo==null){
            repo = SearchRepo.getInstance();
        }
    }

    public void searchRestaurant(String param){
        repo.getLiveRestaurant(param);
    }

    public LiveData<List<RestaurantList.data>> getLiveList(){
        return repo.getLiveList();
    }


}
