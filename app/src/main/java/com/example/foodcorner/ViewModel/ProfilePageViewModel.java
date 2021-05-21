package com.example.foodcorner.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodcorner.Model.NormalResponse;
import com.example.foodcorner.Model.UserProfile;
import com.example.foodcorner.Repository.ProfilePageRepo;

import java.io.File;

public class ProfilePageViewModel extends ViewModel {
    private ProfilePageRepo profilePageRepo;

    public ProfilePageViewModel(){
        profilePageRepo = ProfilePageRepo.getInstance();
    }

    public LiveData<UserProfile> getUserProfile(String name,int id){
        return profilePageRepo.getUserProfile(name,id);
    }

    public LiveData<NormalResponse> updateProfile(String name,int userId,String imageUrl){
        return profilePageRepo.updateProfile(name,userId,imageUrl);
    }

    public LiveData<NormalResponse> uploadImage(File file, String fileName){
        return profilePageRepo.uploadImage(file,fileName);
    }

}
