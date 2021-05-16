package com.example.foodcorner.Network;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
//    public static String BASE_URL = "http://10.0.2.2:47474/";
    public static Retrofit retrofit;

    public static String BASE_URL = "http://192.168.0.104:47474/";

    public static Retrofit getRetrofit(){
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();
        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
        };
        return retrofit;
    }

    public static Retrofit authHeader(String auth){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request newRequest = originalRequest.newBuilder()
                        .header("Authorization","Bearer "+auth)
                        .header("Content-type:application/json","Accept: */*")
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(5,TimeUnit.SECONDS).readTimeout(10,TimeUnit.SECONDS).build();

        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
