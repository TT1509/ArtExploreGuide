package com.example.artexplore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.artexplore.model.User;
import com.example.artexploreguide.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ArtDetailActivity extends AppCompatActivity {
    private static final String TAG = "ArtDetailActivity";
    private boolean isFavorite = false; // To track the favorite status
    private String loggedInUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_detail);

        ImageView artworkImageView = findViewById(R.id.artworkImageView);
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView artistTextView = findViewById(R.id.artistTextView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        ImageButton favoriteButton = findViewById(R.id.favoriteButton);

        // Get logged-in username from SharedPreferences
        SharedPreferences preferences = getSharedPreferences("user_session", Context.MODE_PRIVATE);
        loggedInUsername = preferences.getString("logged_in_username", null);

        if (loggedInUsername == null) {
            Toast.makeText(this, "Error: No user logged in", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Get artwork title from Intent extras
        String artworkTitle = getIntent().getStringExtra("ARTWORK_TITLE");

        // Load the specific artwork details from JSON
        JSONObject artwork = FileManager.loadArtworkFromJson(this, artworkTitle);

        if (artwork != null) {
            try {
                // Set data to views
                titleTextView.setText(artwork.getString("title"));
                String artistName = artwork.getString("artist");
                artistTextView.setText("Artist: " + artistName);
                descriptionTextView.setText(artwork.getString("description"));

                // Load image from drawable resources
                int imageResId = getResources().getIdentifier(artwork.getString("image"), "drawable", getPackageName());
                artworkImageView.setImageResource(imageResId);

                // Make artist name clickable to open ArtistDetailActivity
                artistTextView.setOnClickListener(v -> {
                    Intent intent = new Intent(ArtDetailActivity.this, ArtistDetailActivity.class);
                    intent.putExtra("ARTIST_NAME", artistName);
                    startActivity(intent);
                });

                // Initialize the favorite status
                isFavorite = isArtworkInFavorites(artworkTitle);
                updateFavoriteButton(favoriteButton);

                // Handle favorite button clicks
                favoriteButton.setOnClickListener(v -> {
                    if (isFavorite) {
                        removeArtworkFromFavorites(artworkTitle);
                        isFavorite = false;
                        Toast.makeText(this, "Removed from Favorites", Toast.LENGTH_SHORT).show();
                    } else {
                        addArtworkToFavorites(artworkTitle);
                        isFavorite = true;
                        Toast.makeText(this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                    }
                    updateFavoriteButton(favoriteButton);
                });

            } catch (JSONException e) {
                Log.e(TAG, "Error parsing artwork JSON", e);
            }
        }
    }

    // Update the star icon based on favorite status
    private void updateFavoriteButton(ImageButton button) {
        if (isFavorite) {
            button.setImageResource(R.drawable.ic_star_filled); // Show filled star icon
        } else {
            button.setImageResource(R.drawable.ic_star_outline); // Show outline star icon
        }
    }

    // Check if artwork is in favorites
    private boolean isArtworkInFavorites(String artworkTitle) {
        List<String> favoriteList = FileManager.getFavorites(this, loggedInUsername);
        return favoriteList.contains(artworkTitle);
    }

    // Add artwork to favorites
    private void addArtworkToFavorites(String artworkTitle) {
        FileManager.addArtworkToFavorites(this, loggedInUsername, artworkTitle);
    }

    // Remove artwork from favorites
    private void removeArtworkFromFavorites(String artworkTitle) {
        FileManager.removeArtworkFromFavorites(this, loggedInUsername, artworkTitle);
    }
}
