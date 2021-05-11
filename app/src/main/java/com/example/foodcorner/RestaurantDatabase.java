package com.example.foodcorner;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodcorner.Dao.RestaurantDao;


@Database(entities = {Restaurant.class},version = 1)
public abstract class RestaurantDatabase extends RoomDatabase {
    private static RestaurantDatabase instance;
    public abstract RestaurantDao restaurantDao();
    public static synchronized RestaurantDatabase getInstance(Context context){
        if(instance==null){
            instance =Room.databaseBuilder(context.getApplicationContext(),RestaurantDatabase.class,"restaurant_database").
                    fallbackToDestructiveMigration().build();
        }

        return instance;
    }


}
