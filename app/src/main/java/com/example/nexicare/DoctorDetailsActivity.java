package com.example.nexicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Doctors");

    TextView tv;
    Button btnBACK;
    String title;
    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle);
        btnBACK = findViewById(R.id.buttonLTBack);

        Intent it = getIntent();
        title = it.getStringExtra("title");
        tv.setText(title);

        btnBACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        list = new ArrayList<>();
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );

        ListView lst = findViewById(R.id.listViewLT);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", list.get(i).get("line1").replace("Doctor Name:", ""));
                it.putExtra("text3", list.get(i).get("line2").replace("Hospital Address:", ""));
                it.putExtra("text4", list.get(i).get("line4").replace("Mobile No:", ""));
                it.putExtra("text5", list.get(i).get("line5").replace("Cons Fees:", "").replace("/-", ""));
                startActivity(it);
            }
        });

        // Fetch data from Firebase for the specified specialty
        DatabaseReference specialtyRef = databaseReference.child(title);

        specialtyRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Clear the existing list
                list.clear();

                for (DataSnapshot doctorSnapshot : dataSnapshot.getChildren()) {
                    String doctorName = doctorSnapshot.child("name").getValue(String.class);
                    String experience = doctorSnapshot.child("exp").getValue(String.class);
                    String hospitalAddress = doctorSnapshot.child("hospital_addr").getValue(String.class);
                    long consultationFee = doctorSnapshot.child("con_fee").getValue(Long.class);
                    long mobileNumber = doctorSnapshot.child("mobile").getValue(Long.class);

                    // Add the fetched data to the list
                    item = new HashMap<>();
                    item.put("line1", "Doctor Name:" + doctorName);
                    item.put("line2", "Hospital Address:" + hospitalAddress);
                    item.put("line3", "Exp :" + experience);
                    item.put("line4", "Mobile No:" + mobileNumber);
                    item.put("line5", "Cons Fees:" + consultationFee + "/-");
                    list.add(item);
                }

                // Notify the adapter that the data has changed
                sa.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Log.e("FirebaseError", "Error fetching data: " + databaseError.getMessage());
            }
        });
    }
}
