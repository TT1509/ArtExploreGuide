package com.example.artexplore.model;

import java.util.Date;
import java.util.List;

public class Artist {
    private String name;
    private String biography;
    private Date dateOfBirth;
    private Date dateOfDeath; // Nullable to signify if the artist is still alive
    private int imageResId;
    private List<Artwork> artworks;

    public Artist(String name, String biography, Date dateOfBirth, int imageResId, List<Artwork> artworks) {
        this.name = name;
        this.biography = biography;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = null; // default as null
        this.imageResId = imageResId;
        this.artworks = artworks;
    }

    // Overloaded constructor for artists with a date of death
    public Artist(String name, String biography, Date dateOfBirth, Date dateOfDeath, int imageResId, List<Artwork> artworks) {
        this.name = name;
        this.biography = biography;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.imageResId = imageResId;
        this.artworks = artworks;
    }

    public String getName() {
        return name;
    }

    public String getBiography() {
        return biography;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public int getImageResId() {
        return imageResId;
    }

    public List<Artwork> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<Artwork> artworks) {
        this.artworks = artworks;
    }

    // Optionally, add a method to check if the artist is alive
    public boolean isAlive() {
        return dateOfDeath == null;
    }
}
