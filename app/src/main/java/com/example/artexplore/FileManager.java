package com.example.artexplore;

import android.content.Context;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import org.json.JSONArray;

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
}

