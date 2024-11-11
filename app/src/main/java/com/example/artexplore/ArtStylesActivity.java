package com.example.artexplore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.artexplore.model.ArtStyle;
import com.example.artexploreguide.R;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ArtStylesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_styles);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewArtStyles);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Read art styles from JSON file
        List<ArtStyle> artStyles = FileManager.loadArtStylesFromJson(this);

        ArtStyleAdapter adapter = new ArtStyleAdapter(artStyles, new ArtStyleAdapter.OnItemClickListener() {
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
