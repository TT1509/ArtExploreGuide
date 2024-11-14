package com.example.artexplore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.artexploreguide.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button buttonLogout = findViewById(R.id.logoutButton);

        // Set up click listener for the logout button
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear user session data (if any, e.g., SharedPreferences)
                SharedPreferences preferences = getSharedPreferences("user_session", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear(); // Clear all saved data in SharedPreferences
                editor.apply();

                // Redirect to LoginActivity
                Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear activity stack
                startActivity(intent);
                finish(); // Close MenuActivity
            }
        });

        Button buttonArtStyles = findViewById(R.id.artStylesButton);
        Button buttonArtists = findViewById(R.id.artistsButton);
        Button buttonArtworks = findViewById(R.id.artLocationButton);

        buttonArtStyles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ArtStylesActivity.class);
                intent.putExtra("TARGET_ACTIVITY", "ArtworksActivity");
                startActivity(intent);
            }
        });
        buttonArtists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ArtistsActivity.class);
                startActivity(intent);
            }
        });

        buttonArtworks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ArtStylesActivity.class);
                intent.putExtra("TARGET_ACTIVITY", "ArtStylesDetailActivity");
                startActivity(intent);
            }
        });
    }
}