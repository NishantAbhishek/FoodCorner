package com.example.foodcorner.Network;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
//    public static String BASE_URL = "http://10.0.2.2:47474/";
    public static Retrofit retrofit;
    public static String BASE_URL = "http://192.168.0.102:47474/";

    public static Retrofit getRetrofit(){

        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(100, TimeUnit.SECONDS).readTimeout(100, TimeUnit.SECONDS).build();
        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
        };
        return retrofit;
    }
}
