package com.example.foodcorner.Network;
import com.example.foodcorner.Model.BookingItem;
import com.example.foodcorner.Model.LoginResponse;
import com.example.foodcorner.Model.NormalResponse;
import com.example.foodcorner.Model.RestaurantList;
import com.example.foodcorner.Model.SignResponse;
import com.example.foodcorner.Model.UserProfile;
import com.google.gson.JsonObject;

import org.json.JSONObject;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @POST("/user/bookRestaurant")
    Call<NormalResponse> bookRestaurant(@Body JsonObject body);

    @POST("/user/bookings")
    Call<BookingItem> getBookedRestaurant(@Query("userId")int userId);

    @DELETE("/user/deleteBooked")
    Call<NormalResponse> removeBooking(@Query("userId")int userId,@Query("bookingId")int bookingId);

    @POST("/restaurant/search")
    Call<RestaurantList> searchRestaurant(@Query("value")String param);

    @Multipart
    @POST("/user/profilePic")
    Call<NormalResponse> uploadImage(@Query("fileName")String fileName, @Part MultipartBody.Part photo);

    @POST("/profile/updateProfile")
    Call<NormalResponse> updateProfile(@Query("name")String name,@Query("userId")int userId,@Query("imageUrl")String imageUrl);

    @POST("/profile/getUserInfo")
    Call<UserProfile> getUserProfile(@Query("name")String name,@Query("userId")int userId);
}
