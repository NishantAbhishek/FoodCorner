package com.example.foodcorner.Model;

import com.google.gson.annotations.SerializedName;

public class NormalResponse {
    @SerializedName("StatusCode")
    private int StatusCode;
    @SerializedName("Status")
    private String Status;
    @SerializedName("Message")
    private String Message;

    public NormalResponse(int statusCode, String status, String message) {
        StatusCode = statusCode;
        Status = status;
        Message = message;
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
}
