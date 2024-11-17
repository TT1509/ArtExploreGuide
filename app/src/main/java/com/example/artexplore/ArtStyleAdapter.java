// File: ArtStyleAdapter.java
package com.example.artexplore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artexplore.model.ArtStyle;
import com.example.artexploreguide.R;

import java.util.List;

public class ArtStyleAdapter extends RecyclerView.Adapter<ArtStyleAdapter.ArtStyleViewHolder> {
    private List<ArtStyle> artStyles;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ArtStyle artStyle);
    }

    public ArtStyleAdapter(List<ArtStyle> artStyles, OnItemClickListener listener) {
        this.artStyles = artStyles;
        this.listener = listener;
    }

    // Create a new ViewHolder object
    @NonNull
    @Override
    public ArtStyleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.example.artexploreguide.R.layout.item_art_style, parent, false);
        return new ArtStyleViewHolder(view);
    }

    // Binds data to the ViewHolder for a specific position
    @Override
    public void onBindViewHolder(@NonNull ArtStyleViewHolder holder, int position) {
        ArtStyle artStyle = artStyles.get(position);
        holder.bind(artStyle, listener);
    }

    // Returns the total number of items in the adapter
    @Override
    public int getItemCount() {
        return artStyles.size();
    }

    // ViewHolder class to hold and manage views for each item
    public static class ArtStyleViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ArtStyleViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewArtStyle);
            textView = itemView.findViewById(R.id.textViewArtStyleName);
        }

        // Binds data to the views in the ViewHolder
        public void bind(final ArtStyle artStyle, final OnItemClickListener listener) {
            imageView.setImageResource(artStyle.getImageResId());
            textView.setText(artStyle.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(artStyle);
                }
            });
        }
    }
}
