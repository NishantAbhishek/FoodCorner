package com.example.foodcorner.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserProfile {
    @SerializedName("StatusCode")
    private int StatusCode;
    @SerializedName("Status")
    private String Status;
    @SerializedName("Message")
    private String Message;
    @SerializedName("data")
    private ArrayList<UserProfile.data> data;

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public ArrayList<UserProfile.data> getData() {
        return data;
    }

    public void setData(ArrayList<UserProfile.data> data) {
        this.data = data;
    }

    public static class data{
        @SerializedName("UserId")
        private int UserId;
        @SerializedName("FirstName")
        private String FirstName;
        @SerializedName("LastName")
        private String LastName;
        @SerializedName("Email")
        private String Email;
        @SerializedName("Verified")
        private boolean Verified;
        @SerializedName("Subscription")
        private String Subscription;
        @SerializedName("ImageUrl")
        private String ImageUrl;

        public data(int userId, String firstName, String lastName, String email, boolean verified, String subscription, String imageUrl) {
            UserId = userId;
            FirstName = firstName;
            LastName = lastName;
            Email = email;
            Verified = verified;
            Subscription = subscription;
            ImageUrl = imageUrl;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int userId) {
            UserId = userId;
        }

        public String getFirstName() {
            return FirstName;
        }

        public void setFirstName(String firstName) {
            FirstName = firstName;
        }

        public String getLastName() {
            return LastName;
        }

        public void setLastName(String lastName) {
            LastName = lastName;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public boolean isVerified() {
            return Verified;
        }

        public void setVerified(boolean verified) {
            Verified = verified;
        }

        public String getSubscription() {
            return Subscription;
        }

        public void setSubscription(String subscription) {
            Subscription = subscription;
        }

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String imageUrl) {
            ImageUrl = imageUrl;
        }
    }




}
