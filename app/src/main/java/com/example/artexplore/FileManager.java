package com.example.artexplore;

import android.content.Context;
import android.util.Log;

import com.example.artexplore.model.ArtStyle;
import com.example.artexplore.model.Artist;
import com.example.artexplore.model.Artwork;
import com.example.artexploreguide.R;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class FileManager {

    // Writes data to a file
    public static void writeToFile(String data, Context context, String fileName) {
        try {
            File file = new File(context.getFilesDir(), fileName);
            //Create the file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            }
            try (FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)) {
                fos.write(data.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Reads data from a file
    public static String readFromFile(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileInputStream fis = context.openFileInput(fileName)) {
            int ch;
            while ((ch = fis.read()) != -1) {
                stringBuilder.append((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    // Load artworks from JSON based on selected art style
    public static List<Artwork> loadArtworksFromJson(Context context, String artStyle) {
        List<Artwork> artworks = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            InputStream is = context.getResources().openRawResource(R.raw.artworks);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject artworkObject = jsonArray.getJSONObject(i);
                String artStyleFromJson = artworkObject.getString("artstyle");

                if (artStyleFromJson.equals(artStyle)) {
                    String title = artworkObject.getString("title");
                    String description = artworkObject.getString("description");
                    String image = artworkObject.getString("image");

                    // Parse artist details from the JSON
                    JSONObject artistObject = artworkObject.getJSONObject("artist");
                    String artistName = artistObject.getString("name");
                    String artistBiography = artistObject.getString("biography");
                    String artistImage = artistObject.getString("image");

                    Date dateOfBirth = dateFormat.parse(artistObject.getString("dateOfBirth"));
                    Date dateOfDeath = artistObject.has("dateOfDeath") ? dateFormat.parse(artistObject.getString("dateOfDeath")) : null;

                    int artistImageResId = context.getResources().getIdentifier(artistImage, "drawable", context.getPackageName());
                    int imageResId = context.getResources().getIdentifier(image, "drawable", context.getPackageName());

                    // Create Artist and Artwork instances
                    Artist artist = new Artist(artistName, artistBiography, dateOfBirth, dateOfDeath, artistImageResId, new ArrayList<>());
                    Artwork artwork = new Artwork(title, description, imageResId, artist);

                    // Add artwork to the artist's list and to the result list
                    artist.getArtworks().add(artwork);
                    artworks.add(artwork);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return artworks;
    }

    // Method to load artwork details from artworks.json in res/raw
    public static JSONObject loadArtworkFromJson(Context context, String artworkTitle) {
        try {
            InputStream is = context.getResources().openRawResource(R.raw.artworks);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            JSONArray artworksArray = new JSONArray(json);
            for (int i = 0; i < artworksArray.length(); i++) {
                JSONObject artwork = artworksArray.getJSONObject(i);
                if (artwork.getString("title").equalsIgnoreCase(artworkTitle)) {
                    return artwork;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Load art styles from the JSON file in raw resources
    public static List<ArtStyle> loadArtStylesFromJson(Context context) {
        List<ArtStyle> artStyles = new ArrayList<>();
        try {
            InputStream is = context.getResources().openRawResource(R.raw.artstyles);
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
                int imageResId = context.getResources().getIdentifier(image, "drawable", context.getPackageName());
                artStyles.add(new ArtStyle(name, imageResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return artStyles;
    }
}

