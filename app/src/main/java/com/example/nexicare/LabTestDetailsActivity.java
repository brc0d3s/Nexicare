package com.example.nexicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class LabTestDetailsActivity extends AppCompatActivity {
    TextView tvPackageName,tvTotalCost;
    EditText edDetails;
    Button btnAddToCart,btnBack;
    private DatabaseReference rootDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        // Retrieve the username from the Intent
        String username = getIntent().getStringExtra("username");

        tvPackageName = findViewById(R.id.textViewLDPackageName);
        tvTotalCost = findViewById(R.id.textViewLDTotalCost);
        edDetails = findViewById(R.id.editTextLDTextMultiLine);
        btnAddToCart = findViewById(R.id.buttonLDAddToCart);
        btnBack = findViewById(R.id.buttonLDBack);

        rootDatabaseRef = FirebaseDatabase.getInstance().getReference().child("cart");

        edDetails.setKeyListener(null);

        Intent intent = getIntent();
        String packageName= intent.getStringExtra("text1");
        String description= intent.getStringExtra("text2");
        String cost= intent.getStringExtra("text3");

        tvPackageName.setText(packageName);
        edDetails.setText(description);
        tvTotalCost.setText("Total Cost : "+cost+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
                finish();
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Data Insertion
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("Username", username);
                hashMap.put("Package", packageName);
                hashMap.put("Description", description);
                hashMap.put("Cost", cost);

                // Save the data to Firebase
                String cartKey = rootDatabaseRef.push().getKey(); // Generates a unique key for the appointment
                rootDatabaseRef.child(cartKey).setValue(hashMap);

                // Show a success message
                Toast.makeText(LabTestDetailsActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();

                //startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
            }
        });
    }
}