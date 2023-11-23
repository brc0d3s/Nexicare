package com.example.nexicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailsActivity extends AppCompatActivity {

    private ArrayList<HashMap<String, String>> orderDetailsList = new ArrayList<>();
    private ListView lst;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        btn = findViewById(R.id.buttonODBack);
        lst = findViewById(R.id.listViewOD);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderDetailsActivity.this, HomeActivity.class));
            }
        });

        // Fetch and populate the data from Firebase
        fetchDataFromFirebase();
    }

    private void fetchDataFromFirebase() {
        DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference().child("cart");

        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orderDetailsList.clear(); // Clear the list before adding new data

                for (DataSnapshot orderSnapshot : dataSnapshot.getChildren()) {
                    String cost = orderSnapshot.child("Cost").getValue(String.class);
                    String description = orderSnapshot.child("Description").getValue(String.class);
                    String packageInfo = orderSnapshot.child("Package").getValue(String.class);
                    String user = orderSnapshot.child("User").getValue(String.class);

                    // Hardcoded delivery location as "Nairobi"
                    String deliveryDetails = "Nairobi";

                    // Create a HashMap for each order
                    HashMap<String, String> orderItem = new HashMap<>();
                    orderItem.put("line1", cost);
                    orderItem.put("line2", description);
                    orderItem.put("line3", packageInfo);
                    orderItem.put("line4", "Del:" + deliveryDetails);
                    orderItem.put("line5", user);

                    // Add the HashMap to the list
                    orderDetailsList.add(orderItem);
                }

                // Notify the adapter that the data has changed
                updateListView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors here
            }
        });
    }

    private void updateListView() {
        SimpleAdapter sa = new SimpleAdapter(
                this,
                orderDetailsList,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});

        // Set the adapter for the ListView
        lst.setAdapter(sa);
    }
}
