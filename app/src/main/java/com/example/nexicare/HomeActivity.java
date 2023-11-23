package com.example.nexicare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
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
            Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
        }


        CardView exit = findViewById(R.id.cardExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                finish();
            }
        });



        CardView findDoctor = findViewById(R.id.cardFindDoctor);
        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,FindDoctorActivity.class));
            }
        });


        CardView buyMedicine = findViewById(R.id.cardBuyMedicine);
        buyMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(HomeActivity.this,BuyMedicineActivity.class));
            }
        });


        CardView healthArticles = findViewById(R.id.cardHealthDoctor);
        healthArticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HealthArticlesActivity.class));
            }
        });


        CardView labTest = findViewById(R.id.cardLabTest);
        labTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,LabTestActivity.class));
            }
        });


        CardView orderDetails = findViewById(R.id.cardOrderDetails);
        orderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, OrderDetailsActivity.class));
            }
        });
    }
}
