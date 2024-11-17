package com.example.artexplore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artexplore.model.Artist;
import com.example.artexploreguide.R;

import java.util.ArrayList;
import java.util.List;

// Adapter class for managing a list of Artist objects in a RecyclerView
public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {
    private final List<Artist> artists;
    private List<Artist> filteredArtists;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Artist artist);
    }

    public ArtistAdapter(List<Artist> artists, OnItemClickListener listener) {
        this.artists = artists;
        this.filteredArtists = new ArrayList<>(artists);
        this.listener = listener;
    }

    // Create a new ViewHolder object
    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false);
        return new ArtistViewHolder(view);
    }

    // Binds data to the ViewHolder for a specific position
    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        Artist artist = artists.get(position);
        holder.bind(artist, listener);
    }

    // Returns the total number of items in the adapter
    @Override
    public int getItemCount() {
        return artists.size();
    }

    // Filters the artist list based on a search query
    public void filter(String query) {
        filteredArtists.clear();
        if (query.isEmpty()) {
            filteredArtists.addAll(artists); // Show all artists if query is empty
        } else {
            for (Artist artist : artists) {
                if (artist.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredArtists.add(artist);
                }
            }
        }
        notifyDataSetChanged(); // Notify the adapter to refresh the view
    }

    // ViewHolder class to hold and manage views for each item
    public static class ArtistViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView biographyTextView;
        private final ImageView imageView;

        // Constructor initializes the views
        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.artistName);
            biographyTextView = itemView.findViewById(R.id.artistBiography);
            imageView = itemView.findViewById(R.id.artistImage);
        }

        // Binds data to the views in the ViewHolder
        public void bind(final Artist artist, final OnItemClickListener listener) {
            nameTextView.setText(artist.getName());
            biographyTextView.setText(artist.getBiography());
            imageView.setImageResource(artist.getImageResId());

            // Set a click listener on the entire item view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(artist);
                }
            });
        }
    }
}

