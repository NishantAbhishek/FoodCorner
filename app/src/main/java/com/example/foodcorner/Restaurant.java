package com.example.foodcorner;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Restaurants")
public class Restaurant{

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String Name;
    private int Rating;
    private float Lat;
    private float Lang;
    private String Pricy;
    private String ImageUrl;
    private String RestaurantType;
    private String Annoucement;
    private String AnnoucementDate;
    private  String UpcomingEvent;
    private String EventDate;

    public Restaurant(){

    }

    public Restaurant(int id, String name, int rating, float lat, float lang, String pricy, String imageUrl, String restaurantType, String annoucement, String annoucementDate, String upcomingEvent, String eventDate) {
        this.id = id;
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

    public void setName(String name) {
        Name = name;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public void setLat(float lat) {
        Lat = lat;
    }

    public void setLang(float lang) {
        Lang = lang;
    }

    public void setPricy(String pricy) {
        Pricy = pricy;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public void setRestaurantType(String restaurantType) {
        RestaurantType = restaurantType;
    }

    public void setAnnoucement(String annoucement) {
        Annoucement = annoucement;
    }

    public void setAnnoucementDate(String annoucementDate) {
        AnnoucementDate = annoucementDate;
    }

    public void setUpcomingEvent(String upcomingEvent) {
        UpcomingEvent = upcomingEvent;
    }

    public void setEventDate(String eventDate) {
        EventDate = eventDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public int getRating() {
        return Rating;
    }

    public float getLat() {
        return Lat;
    }

    public float getLang() {
        return Lang;
    }

    public String getPricy() {
        return Pricy;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getRestaurantType() {
        return RestaurantType;
    }

    public String getAnnoucement() {
        return Annoucement;
    }

    public String getAnnoucementDate() {
        return AnnoucementDate;
    }

    public String getUpcomingEvent() {
        return UpcomingEvent;
    }

    public String getEventDate() {
        return EventDate;
    }
}
