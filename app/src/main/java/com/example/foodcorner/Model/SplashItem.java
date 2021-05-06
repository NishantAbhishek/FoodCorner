package com.example.foodcorner.Model;

public class SplashItem {
    private int image;
    private String message;

    public SplashItem(int image, String message) {
        this.image = image;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
