package com.example.artexplore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.artexploreguide.R;
import org.json.JSONException;
import org.json.JSONObject;

public class ArtStylesDetailActivity extends AppCompatActivity {
    private static final String TAG = "ArtStylesDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_styles_detail);

        ImageView artStyleImageView = findViewById(R.id.artStyleImageView);
        TextView artStyleNameTextView = findViewById(R.id.artStyleNameTextView);
        TextView artStyleDescriptionTextView = findViewById(R.id.artStyleDescriptionTextView);

        // Retrieve art style data from intent
        String artStyleName = getIntent().getStringExtra("ART_STYLE_NAME");
        int imageResId = getIntent().getIntExtra("ART_STYLE_IMAGE_RES_ID", 0);
        String description = getIntent().getStringExtra("ART_STYLE_DESCRIPTION");

        // Set the data to views
        artStyleNameTextView.setText(artStyleName);
        artStyleDescriptionTextView.setText(description);
        artStyleImageView.setImageResource(imageResId);
    }
}
