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

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {
    private final List<Artist> artists;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Artist artist);
    }

    public ArtistAdapter(List<Artist> artists, OnItemClickListener listener) {
        this.artists = artists;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        Artist artist = artists.get(position);
        holder.bind(artist, listener);
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView biographyTextView;
        private final ImageView imageView;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.artistName);
            biographyTextView = itemView.findViewById(R.id.artistBiography);
            imageView = itemView.findViewById(R.id.artistImage);
        }

        public void bind(final Artist artist, final OnItemClickListener listener) {
            nameTextView.setText(artist.getName());
            biographyTextView.setText(artist.getBiography());
            imageView.setImageResource(artist.getImageResId());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(artist);
                }
            });
        }
    }
}

