package com.example.nexicare;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Retrieve the username from the Intent
        String username = getIntent().getStringExtra("username");

        if (username != null && !username.isEmpty()) {
            // Display a welcome message as a Toast
            String welcomeMessage = "Welcome, " + username;
            Toast.makeText(getApplicationContext(), welcomeMessage, Toast.LENGTH_SHORT).show();
        } else {
            // Handle the case where username is not passed correctly
            Toast.makeText(getApplicationContext(), "Welcome, User", Toast.LENGTH_SHORT).show();
        }


    }
}
