package com.example.foodcorner.Model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("StatusCode")
    private int StatusCode;
    @SerializedName("Status")
    private String Status;
    @SerializedName("Message")
    private String Message;
    @SerializedName("auth")
    private String auth;
    @SerializedName("Name")
    private String Name;
    @SerializedName("UserId")
    private int UserId;


    public LoginResponse(int statusCode, String status, String message, String auth, String name, int userId) {
        StatusCode = statusCode;
        Status = status;
        Message = message;
        this.auth = auth;
        Name = name;
        UserId = userId;
    }

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

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}
