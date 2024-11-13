package com.example.artexplore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.artexplore.model.ArtStyle;
import com.example.artexploreguide.R;
import java.util.List;

public class ArtStylesActivity extends AppCompatActivity {

    private String targetActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_styles);

        // Retrieve the target activity from the intent
        targetActivity = getIntent().getStringExtra("TARGET_ACTIVITY");

        RecyclerView recyclerView = findViewById(R.id.recyclerViewArtStyles);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Load art styles from JSON
        List<ArtStyle> artStyles = FileManager.loadArtStylesFromJson(this);

        // Set up the adapter with a click listener
        ArtStyleAdapter adapter = new ArtStyleAdapter(artStyles, artStyle -> {
            Intent intent;
            if ("ArtworksActivity".equals(targetActivity)) {
                intent = new Intent(ArtStylesActivity.this, ArtworksActivity.class);
                intent.putExtra("ART_STYLE_NAME", artStyle.getName());
            } else {
                intent = new Intent(ArtStylesActivity.this, ArtStylesDetailActivity.class);
                intent.putExtra("ART_STYLE_NAME", artStyle.getName());
                intent.putExtra("ART_STYLE_IMAGE_RES_ID", artStyle.getImageResId());
                intent.putExtra("ART_STYLE_DESCRIPTION", artStyle.getDescription());
            }
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
    }
}
