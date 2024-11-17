package com.example.artexplore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artexplore.model.Artwork;
import com.example.artexploreguide.R;

import java.util.ArrayList;
import java.util.List;

public class ArtworkAdapter extends RecyclerView.Adapter<ArtworkAdapter.ArtworkViewHolder>{
    private List<Artwork> artworks;
    private List<Artwork> fullArtworkList;
    private OnItemClickListener listener;

    public ArtworkAdapter(List<Artwork> artworks, OnItemClickListener listener) {
        this.artworks = artworks;
        this.fullArtworkList = new ArrayList<>(artworks);
        this.listener = listener;
    }

    // Create a new ViewHolder object
    @Override
    public ArtworkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artwork, parent, false);
        return new ArtworkViewHolder(view);
    }

    // Binds data to the ViewHolder for a specific position
    @Override
    public void onBindViewHolder(ArtworkViewHolder holder, int position) {
        Artwork artwork = artworks.get(position);
        holder.title.setText(artwork.getTitle());
        holder.image.setImageResource(artwork.getImageResId());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(artwork));
    }

    // Returns the total number of items in the adapter
    @Override
    public int getItemCount() {
        return artworks.size();
    }

    // Filters the artwork list based on a search query
    public void filter(String query) {
        artworks.clear();
        if (query.isEmpty()) {
            artworks.addAll(fullArtworkList);
        } else {
            String lowerCaseQuery = query.toLowerCase();
            for (Artwork artwork : fullArtworkList) {
                if (artwork.getTitle().toLowerCase().contains(lowerCaseQuery)) {
                    artworks.add(artwork);
                }
            }
        }
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Artwork artwork);
    }

    // ViewHolder class to hold and manage views for each item
    public static class ArtworkViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageView image;

        public ArtworkViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(com.example.artexploreguide.R.id.artworkTitle);
            image = itemView.findViewById(R.id.artworkImage);
        }
    }
}
