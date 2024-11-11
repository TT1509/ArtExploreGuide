package com.example.artexplore;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.artexploreguide.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ArtDetailActivity extends AppCompatActivity {
    private static final String TAG = "ArtDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_detail);

        ImageView artworkImageView = findViewById(R.id.artworkImageView);
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView artistTextView = findViewById(R.id.artistTextView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);

        // Get artwork title from Intent extras
        String artworkTitle = getIntent().getStringExtra("ARTWORK_TITLE");

        // Load the specific artwork details from JSON
        JSONObject artwork = FileManager.loadArtworkFromJson(this, artworkTitle);

        if (artwork != null) {
            try {
                // Set data to views
                String title = artwork.getString("title");
                String artist = artwork.getString("artist");
                String description = artwork.getString("description");
                String image = artwork.getString("image");

                titleTextView.setText(title);
                artistTextView.setText("Artist: " + artist);
                descriptionTextView.setText(description);

                // Load image from drawable resources
                int imageResId = getResources().getIdentifier(image, "drawable", getPackageName());
                artworkImageView.setImageResource(imageResId);

            } catch (JSONException e) {
                Log.e(TAG, "Error parsing artwork JSON", e);
            }
        }
    }
}