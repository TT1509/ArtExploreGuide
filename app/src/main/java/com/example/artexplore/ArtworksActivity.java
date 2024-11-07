package com.example.artexplore;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artexplore.model.Artwork;
import com.example.artexploreguide.R;
import java.util.ArrayList;
import java.util.List;

public class ArtworksActivity extends AppCompatActivity {

    private String artStyleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artworks);

        // Retrieve the selected art style from the intent
        artStyleName = getIntent().getStringExtra("ART_STYLE_NAME");

        RecyclerView recyclerView = findViewById(R.id.recyclerViewArtworks);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns

        // Create a list of artworks based on the selected art style
        List<Artwork> artworks = new ArrayList<>();
        if ("Impressionism".equals(artStyleName)) {
            artworks.add(new Artwork("Starry Night", "A famous painting by Vincent van Gogh.", R.drawable.sample_image));
            artworks.add(new Artwork("Impression, Sunrise", "A painting by Claude Monet.", R.drawable.sample_image));
        } else if ("Abstract".equals(artStyleName)) {
            artworks.add(new Artwork("Composition X", "A painting by Wassily Kandinsky.", R.drawable.sample_image));
            artworks.add(new Artwork("Squares with Concentric Circles", "A painting by Wassily Kandinsky.", R.drawable.sample_image));
        } else if ("Cubism".equals(artStyleName)) {
            artworks.add(new Artwork("Les Demoiselles d'Avignon", "A painting by Pablo Picasso.", R.drawable.sample_image));
            artworks.add(new Artwork("Violin and Candlestick", "A painting by Georges Braque.", R.drawable.sample_image));
        }

        // Set the adapter for the RecyclerView
        ArtworkAdapter adapter = new ArtworkAdapter(artworks, new ArtworkAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Artwork artwork) {
                // Pass the selected artwork details to ArtDetailActivity
                Intent intent = new Intent(ArtworksActivity.this, ArtDetailActivity.class);
                intent.putExtra("ARTWORK_TITLE", artwork.getTitle());
                intent.putExtra("ARTWORK_DESCRIPTION", artwork.getDescription());
                intent.putExtra("ARTWORK_IMAGE", artwork.getImageResId());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
    }
}
