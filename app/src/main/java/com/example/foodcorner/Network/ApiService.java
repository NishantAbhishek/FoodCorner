package com.example.foodcorner.Network;
import com.example.foodcorner.Model.SignResponse;
import com.google.gson.JsonObject;

import org.json.JSONObject;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST("user/signIn")
    Call<SignResponse> getMovieList(@Body JsonObject body);

    @Headers({"Content-type: application/json", "Accept: */*"})
    @POST("user/authenticate")
    Call<SignResponse> verifyEmail(@Body JsonObject body);
}
