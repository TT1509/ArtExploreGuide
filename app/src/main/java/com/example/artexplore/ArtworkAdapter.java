package com.example.artexplore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artexplore.model.Artwork;
import com.example.artexploreguide.R;

import java.util.List;

public class ArtworkAdapter extends RecyclerView.Adapter<ArtworkAdapter.ArtworkViewHolder>{
    private List<Artwork> artworks;
    private OnItemClickListener listener;

    public ArtworkAdapter(List<Artwork> artworks, OnItemClickListener listener) {
        this.artworks = artworks;
        this.listener = listener;
    }

    @Override
    public ArtworkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artwork, parent, false);
        return new ArtworkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtworkViewHolder holder, int position) {
        Artwork artwork = artworks.get(position);
        holder.title.setText(artwork.getTitle());
        holder.image.setImageResource(artwork.getImageResId());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(artwork));
    }

    @Override
    public int getItemCount() {
        return artworks.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Artwork artwork);
    }

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
