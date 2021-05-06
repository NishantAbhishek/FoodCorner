package com.example.foodcorner.ViewModel;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.foodcorner.Model.SignResponse;
import com.example.foodcorner.Network.ApiService;
import com.example.foodcorner.Network.RetrofitInstance;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignInViewModel extends ViewModel{
    private static final String TAG = ViewModel.class.toString();
    private MutableLiveData<SignResponse> signInResponse;
    private MutableLiveData<SignResponse> authenticationResponse;

    public SignInViewModel() {
        signInResponse = new MutableLiveData<>();
        authenticationResponse = new MutableLiveData<>();
    }

    public MutableLiveData<SignResponse> signInObserver(){
        return  signInResponse;
    }
    public MutableLiveData<SignResponse> verificationObserver(){
        return authenticationResponse;
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
                    signInResponse.postValue(new SignResponse(500,"Failed","Failure"));
                    Log.e(TAG,"Failed");
                }
            }
            @Override
            public void onFailure(Call<SignResponse> call, Throwable t) {
                Log.e(TAG,"Failed "+t.getMessage());
                signInResponse.postValue(new SignResponse(500,"Failed","Failure"));
            }
        });
    }

    public void verifyEmail(JsonObject jsonObject){
        Log.i(TAG,"Verify Email");
        ApiService apiService = RetrofitInstance.getRetrofit().create(ApiService.class);
        Call<SignResponse> call = apiService.verifyEmail(jsonObject);
        call.enqueue(new Callback<SignResponse>() {
            @Override
            public void onResponse(Call<SignResponse> call, Response<SignResponse> response) {
                if(response.isSuccessful()){
                    Log.e(TAG,response.message());
                    authenticationResponse.postValue(response.body());
                }else{
                    Log.e(TAG,"Failed--");
                }
            }
            @Override
            public void onFailure(Call<SignResponse> call, Throwable t) {
                Log.e(TAG,"Failed"+t.getMessage());
            }
        });
    }


}
