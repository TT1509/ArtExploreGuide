package com.example.artexplore;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.artexplore.model.Artwork;
import com.example.artexploreguide.R;
import com.example.artexplore.model.Artist;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ArtistDetailActivity extends AppCompatActivity {
    private static final String TAG = "ArtistDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_detail);

        ImageView artistImageView = findViewById(R.id.artistImageView);
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView biographyTextView = findViewById(R.id.biographyTextView);
        TextView dateOfBirthTextView = findViewById(R.id.dateOfBirthTextView);
        TextView dateOfDeathTextView = findViewById(R.id.dateOfDeathTextView);
        TextView artworksListTextView = findViewById(R.id.artworksListTextView);

        // Get artist name from Intent extras
        String artistName = getIntent().getStringExtra("ARTIST_NAME");

        // Load the specific artist details from JSON
        Artist artist = FileManager.loadArtistFromJson(this, artistName);

        if (artist != null) {
            // Set artist data to views
            nameTextView.setText(artist.getName());
            biographyTextView.setText(artist.getBiography());

            // Format and display date of birth
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
            dateOfBirthTextView.setText("Date of Birth: " + dateFormat.format(artist.getDateOfBirth()));

            // If the artist is deceased, display date of death
            if (artist.getDateOfDeath() != null) {
                dateOfDeathTextView.setText("Date of Death: " + dateFormat.format(artist.getDateOfDeath()));
            } else {
                dateOfDeathTextView.setText("Alive");
            }

            // Load image from drawable resources
            artistImageView.setImageResource(artist.getImageResId());

            // Display artworks titles
            StringBuilder artworksListBuilder = new StringBuilder();
            for (Artwork artwork : artist.getArtworks()) {
                artworksListBuilder.append("• ").append(artwork.getTitle()).append("\n");
            }
            artworksListTextView.setText(artworksListBuilder.toString());
        } else {
            Log.e(TAG, "Artist details not found for name: " + artistName);
        }
    }
}
