package com.example.foodcorner.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RestaurantList {

    @SerializedName("StatusCode")
    private int StatusCode;

    @SerializedName("Status")
    private String Status;

    @SerializedName("Message")
    private String Message;

    @Expose
    @SerializedName("data")
    private List<data> data;

    public static class data{
        @SerializedName("Name")
        private String Name;
        @SerializedName("Rating")
        private int Rating;
        @SerializedName("Lat")
        private float Lat;
        @SerializedName("Lang")
        private float Lang;
        @SerializedName("Pricy")
        private String Pricy;
        @SerializedName("ImageUrl")
        private String ImageUrl;
        @SerializedName("RestaurantType")
        private String RestaurantType;
        @SerializedName("Annoucement")
        private String Annoucement;
        @SerializedName("AnnoucementDate")
        private String AnnoucementDate;
        @SerializedName("UpcomingEvent")
        private  String UpcomingEvent;
        @SerializedName("EventDate")
        private String EventDate;

        public data(String name, int rating, float lat, float lang, String pricy, String imageUrl, String restaurantType, String annoucement, String annoucementDate, String upcomingEvent, String eventDate) {
            Name = name;
            Rating = rating;
            Lat = lat;
            Lang = lang;
            Pricy = pricy;
            ImageUrl = imageUrl;
            RestaurantType = restaurantType;
            Annoucement = annoucement;
            AnnoucementDate = annoucementDate;
            UpcomingEvent = upcomingEvent;
            EventDate = eventDate;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public int getRating() {
            return Rating;
        }

        public void setRating(int rating) {
            Rating = rating;
        }

        public float getLat() {
            return Lat;
        }

        public void setLat(float lat) {
            Lat = lat;
        }

        public float getLang() {
            return Lang;
        }

        public void setLang(float lang) {
            Lang = lang;
        }

        public String getPricy() {
            return Pricy;
        }

        public void setPricy(String pricy) {
            Pricy = pricy;
        }

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String imageUrl) {
            ImageUrl = imageUrl;
        }

        public String getRestaurantType() {
            return RestaurantType;
        }

        public void setRestaurantType(String restaurantType) {
            RestaurantType = restaurantType;
        }

        public String getAnnoucement() {
            return Annoucement;
        }

        public void setAnnoucement(String annoucement) {
            Annoucement = annoucement;
        }

        public String getAnnoucementDate() {
            return AnnoucementDate;
        }

        public void setAnnoucementDate(String annoucementDate) {
            AnnoucementDate = annoucementDate;
        }

        public String getUpcomingEvent() {
            return UpcomingEvent;
        }

        public void setUpcomingEvent(String upcomingEvent) {
            UpcomingEvent = upcomingEvent;
        }

        public String getEventDate() {
            return EventDate;
        }

        public void setEventDate(String eventDate) {
            EventDate = eventDate;
        }
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

    public List<RestaurantList.data> getData() {
        return data;
    }

    public void setData(ArrayList<RestaurantList.data> data) {
        this.data = data;
    }
}
