// File: LoginActivity.java
package com.example.artexplore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.artexploreguide.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private static final String FILE_NAME = "users.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.artexploreguide.R.layout.activity_login);

        // Initialize UI components
        EditText usernameEditText = findViewById(R.id.loginUsernameEditText);
        EditText passwordEditText = findViewById(R.id.loginPasswordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        TextView navigateToSignUp = findViewById(R.id.navigateToSignUp);

        // Set click listener for Login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isValidUser(username, password)) {
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    // Navigate to MenuActivity
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set click listener for navigating to SignUpActivity
        navigateToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SignUpActivity
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to validate user credentials
    private boolean isValidUser(String username, String password) {
        String data = FileManager.readFromFile(this, FILE_NAME);
        if (data.isEmpty()) {
            return false;
        }

        try {
            JSONArray usersArray = new JSONArray(data);
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userObject = usersArray.getJSONObject(i);
                String storedUsername = userObject.getString("username");
                String storedPassword = userObject.getString("password");

                if (storedUsername.equals(username) && storedPassword.equals(password)) {
                    return true;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
