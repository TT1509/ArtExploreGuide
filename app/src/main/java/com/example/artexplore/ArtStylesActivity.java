package com.example.artexplore;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import com.example.artexplore.model.ArtStyle;
import com.example.artexploreguide.R;

public class ArtStylesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_styles);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewArtStyles);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        List<ArtStyle> artStyles = new ArrayList<>();
        artStyles.add(new ArtStyle("Impressionism", R.drawable.impressionism));
        artStyles.add(new ArtStyle("Abstract", R.drawable.abstract1));
        artStyles.add(new ArtStyle("Cubism", R.drawable.cubism));

        ArtStyleAdapter adapter = new ArtStyleAdapter(artStyles, new ArtStyleAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(ArtStyle artStyle) {
                Intent intent = new Intent(ArtStylesActivity.this, ArtworksActivity.class);
                intent.putExtra("ART_STYLE_NAME", artStyle.getName());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
