package com.example.artexplore.model;

public class Artwork {
    private String title;
    private String description;
    private int imageResId;

    public Artwork(String title, String description, int imageResId) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }
}
