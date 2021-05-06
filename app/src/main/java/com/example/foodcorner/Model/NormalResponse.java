package com.example.foodcorner.Model;

import com.google.gson.annotations.SerializedName;

public class NormalResponse {
    @SerializedName("StatusCode")
    private int StatusCode;
    @SerializedName("Status")
    private String Status;
    @SerializedName("Message")
    private String Message;


}
