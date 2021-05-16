package com.example.foodcorner.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookingItem {

    @SerializedName("StatusCode")
    private int StatusCode;
    @SerializedName("Status")
    private String Status;
    @SerializedName("Message")
    private String Message;
    @SerializedName("data")
    private ArrayList<BookingItem.data> data = new ArrayList<>();

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

    public ArrayList<BookingItem.data> getData() {
        return data;
    }

    public void setData(ArrayList<BookingItem.data> data) {
        this.data = data;
    }

    public static class data{
        @SerializedName("BookingId")
        private int  BookingId;
        @SerializedName("Userid")
        private int  Userid;
        @SerializedName("RestauruntId")
        private int RestauruntId;
        @SerializedName("NumberOfTable")
        private int NumberOfTable;
        @SerializedName("DateBooked")
        private String DateBooked;
        @SerializedName("TimeBooked")
        private String TimeBooked;
        @SerializedName("RestaurantName")
        private String RestaurantName;
        @SerializedName("Rating")
        private int Rating;
        @SerializedName("ImageUrl")
        private String ImageUrl;
        @SerializedName("RestaurantType")
        private String RestaurantType;
        @SerializedName("BookingType")
        private String BookingType;

        public data(int bookingId, int userid, int restauruntId, int numberOfTable, String dateBooked, String timeBooked, String restaurantName, int rating, String imageUrl, String restaurantType, String bookingType) {
            BookingId = bookingId;
            Userid = userid;
            RestauruntId = restauruntId;
            NumberOfTable = numberOfTable;
            DateBooked = dateBooked;
            TimeBooked = timeBooked;
            RestaurantName = restaurantName;
            Rating = rating;
            ImageUrl = imageUrl;
            RestaurantType = restaurantType;
            BookingType = bookingType;
        }

        public int getBookingId() {
            return BookingId;
        }

        public void setBookingId(int bookingId) {
            BookingId = bookingId;
        }

        public int getUserid() {
            return Userid;
        }

        public void setUserid(int userid) {
            Userid = userid;
        }

        public int getRestauruntId() {
            return RestauruntId;
        }

        public void setRestauruntId(int restauruntId) {
            RestauruntId = restauruntId;
        }

        public int getNumberOfTable() {
            return NumberOfTable;
        }

        public void setNumberOfTable(int numberOfTable) {
            NumberOfTable = numberOfTable;
        }

        public String getDateBooked() {
            return DateBooked;
        }

        public void setDateBooked(String dateBooked) {
            DateBooked = dateBooked;
        }

        public String getTimeBooked() {
            return TimeBooked;
        }

        public void setTimeBooked(String timeBooked) {
            TimeBooked = timeBooked;
        }

        public String getRestaurantName() {
            return RestaurantName;
        }

        public void setRestaurantName(String restaurantName) {
            RestaurantName = restaurantName;
        }

        public int getRating() {
            return Rating;
        }

        public void setRating(int rating) {
            Rating = rating;
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

        public String getBookingType() {
            return BookingType;
        }

        public void setBookingType(String bookingType) {
            BookingType = bookingType;
        }
    }

}
