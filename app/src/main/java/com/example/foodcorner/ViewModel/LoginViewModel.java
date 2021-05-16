package com.example.foodcorner.ViewModel;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.foodcorner.Model.LoginResponse;
import com.example.foodcorner.Network.ApiService;
import com.example.foodcorner.Network.RetrofitInstance;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel{
    private static final String TAG =LoginViewModel.class.toString();
    private MutableLiveData<LoginResponse> loginResponse;

    public LoginViewModel(){
        loginResponse  = new MutableLiveData<>();
    }

    public  MutableLiveData<LoginResponse> loginObserver(){
        return loginResponse;
    }

    public void loginApiCall(JsonObject jsonObject){
        Log.i(TAG,"Login Called:--"+jsonObject.toString());
        ApiService apiService = RetrofitInstance.getRetrofit().create(ApiService.class);
        Call<LoginResponse> call = apiService.loginUser(jsonObject);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    loginResponse.postValue(response.body());
                }else{
                    Log.e(TAG,"Failed");
                    loginResponse.postValue(new LoginResponse(500,"Failure","Failure","","",-1));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG,"Failed "+t.getMessage());
                loginResponse.postValue(new LoginResponse(500,"Failure","Failure","","",-1));
            }
        });

    }

}
