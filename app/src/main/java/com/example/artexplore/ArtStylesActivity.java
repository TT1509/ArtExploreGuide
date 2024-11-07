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
        List<ArtStyle> artStyles = loadArtStylesFromJson();

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

    // Load art styles from the JSON file in raw resources
    private List<ArtStyle> loadArtStylesFromJson() {
        List<ArtStyle> artStyles = new ArrayList<>();
        try {
            InputStream is = getResources().openRawResource(R.raw.artstyles);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject artStyleObject = jsonArray.getJSONObject(i);
                String name = artStyleObject.getString("name");
                String image = artStyleObject.getString("image");

                // Add artStyle object to list (use image reference in drawable)
                int imageResId = getResources().getIdentifier(image, "drawable", getPackageName());
                artStyles.add(new ArtStyle(name, imageResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return artStyles;
    }
}
