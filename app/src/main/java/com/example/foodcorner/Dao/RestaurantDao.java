package com.example.foodcorner.Dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodcorner.Restaurant;

import java.util.List;

@Dao
public interface RestaurantDao{
    @Insert
    void insert(Restaurant restaurant);

    @Update
    void update(Restaurant restaurant);

    @Delete
    void delete(Restaurant restaurant);

    @Query("DELETE FROM Restaurants")
    void deleteAllRestaurant();

    @Query("SELECT * FROM Restaurants ORDER BY Name DESC")
    LiveData<List<Restaurant>> getAllRestaurant();


}
