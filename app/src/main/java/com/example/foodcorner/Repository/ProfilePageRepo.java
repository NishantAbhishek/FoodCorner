package com.example.foodcorner.Repository;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodcorner.Model.NormalResponse;
import com.example.foodcorner.Model.UserProfile;
import com.example.foodcorner.Network.ApiService;
import com.example.foodcorner.Network.RetrofitInstance;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfilePageRepo {
    private static ProfilePageRepo instance;
    private MutableLiveData<NormalResponse> imageMessage;
    private static final String TAG = "ProfilePageRepo";
    private MutableLiveData<NormalResponse> updateProfile;
    private MutableLiveData<UserProfile> userProfile;

    public static ProfilePageRepo getInstance(){
        if(instance==null){
            instance = new ProfilePageRepo();
        }
        return instance;
    }

    private ProfilePageRepo(){
        imageMessage = new MutableLiveData<>();
        updateProfile = new MutableLiveData<>();
        userProfile = new MutableLiveData<>();
    }

    public LiveData<NormalResponse> uploadImage(File file, String fileName){
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);
        MultipartBody.Part parts = MultipartBody.Part.createFormData("photo",file.getName(),requestBody);

//        RequestBody someData = RequestBody.create(MediaType.parse("text/plain"),"Profile Image");
        Retrofit retrofit = RetrofitInstance.getRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);

        Call<NormalResponse> responseBodyCall = apiService.uploadImage(fileName,parts);
        responseBodyCall.enqueue(new Callback<NormalResponse>() {
            @Override
            public void onResponse(Call<NormalResponse> call, Response<NormalResponse> response) {
                if(response.isSuccessful()){
                    imageMessage.postValue(response.body());
                }else{
                    Log.e(TAG,response.message());
                    imageMessage.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<NormalResponse> call, Throwable t) {
                Log.e(TAG,t.getMessage());
                imageMessage.postValue(null);
            }
        });
        return imageMessage;
    }

    public LiveData<NormalResponse> updateProfile(String name,int userId,String imageUrl){
        Retrofit retrofit = RetrofitInstance.getRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.updateProfile(name,userId,imageUrl).enqueue(new Callback<NormalResponse>() {
            @Override
            public void onResponse(Call<NormalResponse> call, Response<NormalResponse> response) {
                if(response.isSuccessful()){
                    updateProfile.postValue(response.body());
                }else{
                    updateProfile.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<NormalResponse> call, Throwable t) {
                updateProfile.postValue(null);
            }
        });
        return updateProfile;
    }

    public LiveData<UserProfile> getUserProfile(String name,int userId){
        Retrofit retrofit = RetrofitInstance.getRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);
        retrofit.create(ApiService.class).getUserProfile(name,userId).enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()){
                    userProfile.postValue(response.body());
                }else {
                    userProfile.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                userProfile.postValue(null);
            }
        });
        return userProfile;
    }

}
