package com.example.artexplore;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artexplore.model.Artwork;
import com.example.artexploreguide.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
        List<Artwork> artworks = loadArtworksFromJson(selectedArtStyle);

        ArtworkAdapter adapter = new ArtworkAdapter(artworks, new ArtworkAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Artwork artwork) {
                Intent intent = new Intent(ArtworksActivity.this, ArtDetailActivity.class);
                intent.putExtra("ARTWORK_TITLE", artwork.getTitle());
                intent.putExtra("ARTWORK_DESCRIPTION", artwork.getDescription());
                intent.putExtra("ARTWORK_IMAGE", artwork.getImageResId());
                intent.putExtra("ARTWORK_ARTIST", artwork.getArtist());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    // Load artworks from JSON based on selected art style
    private List<Artwork> loadArtworksFromJson(String artStyle) {
        List<Artwork> artworks = new ArrayList<>();
        try {
            InputStream is = getResources().openRawResource(R.raw.artworks);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject artworkObject = jsonArray.getJSONObject(i);
                String artStyleFromJson = artworkObject.getString("artstyle");

                if (artStyleFromJson.equals(artStyle)) {
                    String title = artworkObject.getString("title");
                    String description = artworkObject.getString("description");
                    String image = artworkObject.getString("image");
                    String artist = artworkObject.getString("artist");

                    int imageResId = getResources().getIdentifier(image, "drawable", getPackageName());
                    artworks.add(new Artwork(title, description, imageResId, artist));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return artworks;
    }
}
