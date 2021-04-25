package com.example.foodcorner.ViewModel;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.foodcorner.Model.SignResponse;
import com.example.foodcorner.Network.ApiService;
import com.example.foodcorner.Network.RetrofitInstance;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInViewModel extends ViewModel {
    private static final String TAG = ViewModel.class.toString();
    private MutableLiveData<SignResponse> signInResponse;

    public SignInViewModel() {
        signInResponse = new MutableLiveData<>();
    }

    public MutableLiveData<SignResponse> signInObserver(){
        return  signInResponse;
    }

    public void signApiCall(JsonObject jsonObject){
        Log.e(TAG,"SignInCalled");
        jsonObject.get("FirstName");
        ApiService  apiService = RetrofitInstance.getRetrofit().create(ApiService.class);
        Call<SignResponse> call = apiService.getMovieList(jsonObject);
        call.enqueue(new Callback<SignResponse>() {
            @Override
            public void onResponse(Call<SignResponse> call, Response<SignResponse> response) {
                if(response.isSuccessful()){
                    signInResponse.postValue(response.body());
                }else{
                    Log.e(TAG,"Failed");
                }
            }
            @Override
            public void onFailure(Call<SignResponse> call, Throwable t) {
                Log.e(TAG,"Failed "+t.getMessage());
            }
        });
    }

}
