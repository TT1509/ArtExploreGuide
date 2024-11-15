package com.example.artexplore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artexploreguide.R;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private final List<String> favoriteList;

    public FavoriteAdapter(List<String> favoriteList) {
        this.favoriteList = favoriteList;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        String artworkTitle = favoriteList.get(position);
        holder.favoriteTitleTextView.setText(artworkTitle);

        // Set a click listener to navigate to ArtDetailActivity
        holder.itemView.setOnClickListener(v -> {
            Context context = holder.itemView.getContext();
            Intent intent = new Intent(context, ArtDetailActivity.class);
            intent.putExtra("ARTWORK_TITLE", artworkTitle);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView favoriteTitleTextView;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            favoriteTitleTextView = itemView.findViewById(R.id.favoriteTitleTextView);
        }
    }
}
