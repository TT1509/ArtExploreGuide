package com.example.artexplore;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artexplore.model.Artist;
import com.example.artexploreguide.R;

import java.util.ArrayList;
import java.util.List;

public class ArtistsActivity extends AppCompatActivity {
    private ArtistAdapter adapter;
    private List<Artist> artists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewArtists);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns

        // Load artists data from JSON using FileManager
        artists = FileManager.loadArtistsFromJson(this);

        adapter = new ArtistAdapter(artists, new ArtistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Artist artist) {
                Intent intent = new Intent(ArtistsActivity.this, ArtistDetailActivity.class);
                intent.putExtra("ARTIST_NAME", artist.getName());
                intent.putExtra("ARTIST_BIOGRAPHY", artist.getBiography());
                intent.putExtra("ARTIST_IMAGE", artist.getImageResId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        // Search Function
        SearchView searchView = findViewById(R.id.searchViewArtists);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return false;
            }
        });
    }
}
