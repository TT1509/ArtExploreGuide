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

    @NonNull
    @Override
    public ArtStyleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.example.artexploreguide.R.layout.item_art_style, parent, false);
        return new ArtStyleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtStyleViewHolder holder, int position) {
        ArtStyle artStyle = artStyles.get(position);
        holder.bind(artStyle, listener);
    }

    @Override
    public int getItemCount() {
        return artStyles.size();
    }

    public static class ArtStyleViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ArtStyleViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewArtStyle);
            textView = itemView.findViewById(R.id.textViewArtStyleName);
        }

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
