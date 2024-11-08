package com.example.artexplore.model;

public class Artwork {
    private String title;
    private String description;
    private int imageResId;
    private String artist;

    public Artwork(String title, String description, int imageResId, String artist) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
        this.artist = artist;
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

    public String getArtist() {
        return artist;
    }
}
