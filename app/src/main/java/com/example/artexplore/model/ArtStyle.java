package com.example.artexplore.model;

public class ArtStyle {
    private String name;
    private int imageResId;

    public ArtStyle(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }
}
