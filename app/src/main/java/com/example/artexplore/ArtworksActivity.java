package com.example.artexplore;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artexplore.model.Artwork;
import com.example.artexplore.model.Artist;
import com.example.artexploreguide.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArtworksActivity extends AppCompatActivity {

    private String selectedArtStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artworks);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewArtworks);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns

        // Get the selected art style from the intent
        selectedArtStyle = getIntent().getStringExtra("ART_STYLE_NAME");

        // Load artworks based on selected art style
        List<Artwork> artworks = FileManager.loadArtworksFromJson(this, selectedArtStyle);

        ArtworkAdapter adapter = new ArtworkAdapter(artworks, new ArtworkAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Artwork artwork) {
                Intent intent = new Intent(ArtworksActivity.this, ArtDetailActivity.class);
                intent.putExtra("ARTWORK_TITLE", artwork.getTitle());
                intent.putExtra("ARTWORK_DESCRIPTION", artwork.getDescription());
                intent.putExtra("ARTWORK_IMAGE", artwork.getImageResId());
                intent.putExtra("ARTIST_NAME", artwork.getArtist().getName());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
