// File: SignUpActivity.java
package com.example.artexplore;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.artexplore.model.User;
import com.example.artexploreguide.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    private static final String FILE_NAME = "users.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize UI components
        EditText usernameEditText = findViewById(R.id.signUpUsernameEditText);
        EditText passwordEditText = findViewById(R.id.signUpPasswordEditText);
        EditText confirmPasswordEditText = findViewById(R.id.signUpConfirmPasswordEditText);
        Button signUpButton = findViewById(R.id.signUpButton);

        // Set click listener for Sign Up button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                // Validate inputs
                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isUsernameTaken(username)) {
                    Toast.makeText(SignUpActivity.this, "Username already taken", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Save the new user
                saveUser(new User(username, password));
                Toast.makeText(SignUpActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();

                // Optionally, navigate back to LoginActivity
                finish(); // Closes SignUpActivity and returns to LoginActivity
            }
        });
    }

    // Method to check if username is already taken
    private boolean isUsernameTaken(String username) {
        String data = FileManager.readFromFile(this, FILE_NAME);
        if (data.isEmpty()) {
            return false;
        }

        try {
            JSONArray usersArray = new JSONArray(data);
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userObject = usersArray.getJSONObject(i);
                String storedUsername = userObject.getString("username");
                if (storedUsername.equals(username)) {
                    return true;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Method to save a new user to the JSON file
    private void saveUser(User user) {
        String data = FileManager.readFromFile(this, FILE_NAME);
        JSONArray usersArray;

        try {
            usersArray = new JSONArray(data);
        } catch (JSONException e) {
            usersArray = new JSONArray(); // Initialize if file is empty or corrupted
        }

        JSONObject newUser = new JSONObject();
        try {
            newUser.put("username", user.getUsername());
            newUser.put("password", user.getPassword());
            usersArray.put(newUser);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Write updated JSON array back to file
        FileManager.writeToFile(usersArray.toString(), this, FILE_NAME);
    }
}
