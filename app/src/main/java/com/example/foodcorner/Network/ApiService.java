package com.example.foodcorner.Network;
import com.example.foodcorner.Model.LoginResponse;
import com.example.foodcorner.Model.RestaurantList;
import com.example.foodcorner.Model.SignResponse;
import com.google.gson.JsonObject;

import org.json.JSONObject;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST("user/signIn")
    Call<SignResponse> getMovieList(@Body JsonObject body);

    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST("user/authenticate")
    Call<SignResponse> verifyEmail(@Body JsonObject body);

    @Headers({"Content-type: application/json","Accept: */*"})
    @POST("user/login")
    Call<LoginResponse> loginUser(@Body JsonObject body);

    @POST("/restaurant/getRestaurant")
    Call<RestaurantList> getRestaurant(@Query("page")int page,@Query("limit")int limit);

    @POST("/restaurant/topRated")
    Call<RestaurantList> getTopRatedRestaurant(@Query("page")int page,@Query("limit")int limit);

    @POST("/restaurant/lowPrice")
    Call<RestaurantList> getLowPriceRestaurant(@Query("page")int page,@Query("limit")int limit);

    @POST("/restaurant/highPrice")
    Call<RestaurantList> getHighPriceRestaurant(@Query("page")int page,@Query("limit")int limit);

    @POST("/restaurant/restaurantId")
    Call<RestaurantList> getRestaurantById(@Query("id") int id);

}
