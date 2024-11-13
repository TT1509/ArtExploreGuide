package com.example.artexplore;

import android.content.Intent;
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