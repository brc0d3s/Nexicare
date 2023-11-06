package com.example.nexicare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FindDoctorActivity extends AppCompatActivity {

    DatabaseReference rootDatabaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);


        CardView back = findViewById(R.id.cardFDBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDoctorActivity.this,HomeActivity.class));
            }
        });


        CardView familyPhysician = findViewById(R.id.cardFDFamilyPhysician);
        familyPhysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Family Physicians");
                rootDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Doc_speacialization/fam_phyc");
                rootDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot doctorSnapshot : dataSnapshot.getChildren()) {
                                String doctorName = doctorSnapshot.child("name").getValue(String.class);
                                String consultationFee = doctorSnapshot.child("con_fee").getValue(String.class);
                                String experience = doctorSnapshot.child("exp").getValue(String.class);
                                String hospitalAddress = doctorSnapshot.child("hospital_addr").getValue(String.class);
                                String mobileNumber = doctorSnapshot.child("mobile").getValue(String.class);

                                // Create an intent for each doctor and pass the data
                                Intent doctorIntent = new Intent(it);
                                doctorIntent.putExtra("doctorName", doctorName);
                                doctorIntent.putExtra("consultationFee", consultationFee);
                                doctorIntent.putExtra("experience", experience);
                                doctorIntent.putExtra("hospitalAddress", hospitalAddress);
                                doctorIntent.putExtra("mobileNumber", mobileNumber);

                                // Start DoctorDetailsActivity for each doctor
                                startActivity(doctorIntent);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle any errors that may occur
                    }
                });
            }
        });



        CardView dietician = findViewById(R.id.cardFDDietician);
        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = (new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class));
                it.putExtra("title","Dietician");
                rootDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Doc_speacialization/Dietician");
                rootDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot doctorSnapshot : dataSnapshot.getChildren()) {
                                String doctorName = doctorSnapshot.child("name").getValue(String.class);
                                String consultationFee = doctorSnapshot.child("con_fee").getValue(String.class);
                                String experience = doctorSnapshot.child("exp").getValue(String.class);
                                String hospitalAddress = doctorSnapshot.child("hospital_addr").getValue(String.class);
                                String mobileNumber = doctorSnapshot.child("mobile").getValue(String.class);

                                // Create an intent for each doctor and pass the data
                                Intent doctorIntent = new Intent(it);
                                doctorIntent.putExtra("doctorName", doctorName);
                                doctorIntent.putExtra("consultationFee", consultationFee);
                                doctorIntent.putExtra("experience", experience);
                                doctorIntent.putExtra("hospitalAddress", hospitalAddress);
                                doctorIntent.putExtra("mobileNumber", mobileNumber);

                                // Start DoctorDetailsActivity for each doctor
                                startActivity(doctorIntent);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle any errors that may occur
                    }
                });
            }
        });


        CardView dentist = findViewById(R.id.cardFDDentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = (new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class));
                it.putExtra("title","Dentist");
                rootDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Doc_speacialization/Dentist");
                rootDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot doctorSnapshot : dataSnapshot.getChildren()) {
                                String doctorName = doctorSnapshot.child("name").getValue(String.class);
                                String consultationFee = doctorSnapshot.child("con_fee").getValue(String.class);
                                String experience = doctorSnapshot.child("exp").getValue(String.class);
                                String hospitalAddress = doctorSnapshot.child("hospital_addr").getValue(String.class);
                                String mobileNumber = doctorSnapshot.child("mobile").getValue(String.class);

                                // Create an intent for each doctor and pass the data
                                Intent doctorIntent = new Intent(it);
                                doctorIntent.putExtra("doctorName", doctorName);
                                doctorIntent.putExtra("consultationFee", consultationFee);
                                doctorIntent.putExtra("experience", experience);
                                doctorIntent.putExtra("hospitalAddress", hospitalAddress);
                                doctorIntent.putExtra("mobileNumber", mobileNumber);

                                // Start DoctorDetailsActivity for each doctor
                                startActivity(doctorIntent);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle any errors that may occur
                    }
                });
            }
        });



        CardView surgeon = findViewById(R.id.cardFDSurgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = (new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class));
                it.putExtra("title","Surgeon");
                rootDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Doc_speacialization/Surgeon");
                rootDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot doctorSnapshot : dataSnapshot.getChildren()) {
                                String doctorName = doctorSnapshot.child("name").getValue(String.class);
                                String consultationFee = doctorSnapshot.child("con_fee").getValue(String.class);
                                String experience = doctorSnapshot.child("exp").getValue(String.class);
                                String hospitalAddress = doctorSnapshot.child("hospital_addr").getValue(String.class);
                                String mobileNumber = doctorSnapshot.child("mobile").getValue(String.class);

                                // Create an intent for each doctor and pass the data
                                Intent doctorIntent = new Intent(it);
                                doctorIntent.putExtra("doctorName", doctorName);
                                doctorIntent.putExtra("consultationFee", consultationFee);
                                doctorIntent.putExtra("experience", experience);
                                doctorIntent.putExtra("hospitalAddress", hospitalAddress);
                                doctorIntent.putExtra("mobileNumber", mobileNumber);

                                // Start DoctorDetailsActivity for each doctor
                                startActivity(doctorIntent);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle any errors that may occur
                    }
                });
            }
        });


        CardView cardiologists = findViewById(R.id.cardFDCardiologists);
        cardiologists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = (new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class));
                it.putExtra("title","Cardiologists");
                rootDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Doc_speacialization/Cardiologist");
                rootDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot doctorSnapshot : dataSnapshot.getChildren()) {
                                String doctorName = doctorSnapshot.child("name").getValue(String.class);
                                String consultationFee = doctorSnapshot.child("con_fee").getValue(String.class);
                                String experience = doctorSnapshot.child("exp").getValue(String.class);
                                String hospitalAddress = doctorSnapshot.child("hospital_addr").getValue(String.class);
                                String mobileNumber = doctorSnapshot.child("mobile").getValue(String.class);

                                // Create an intent for each doctor and pass the data
                                Intent doctorIntent = new Intent(it);
                                doctorIntent.putExtra("doctorName", doctorName);
                                doctorIntent.putExtra("consultationFee", consultationFee);
                                doctorIntent.putExtra("experience", experience);
                                doctorIntent.putExtra("hospitalAddress", hospitalAddress);
                                doctorIntent.putExtra("mobileNumber", mobileNumber);

                                // Start DoctorDetailsActivity for each doctor
                                startActivity(doctorIntent);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle any errors that may occur
                    }
                });
            }
        });
    }
}