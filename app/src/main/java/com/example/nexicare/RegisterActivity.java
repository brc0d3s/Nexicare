package com.example.nexicare;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private DatabaseReference rootDatabaseRef;
    EditText edUsername, edEmail, edPassword, edConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextRegUsername);
        edPassword = findViewById(R.id.editTextRegPassword);
        edConfirm = findViewById(R.id.editTextRegConfirmPassword);
        edEmail = findViewById(R.id.editTextRegUserEmail);
        btn = findViewById(R.id.buttonRegister);
        tv = findViewById(R.id.textViewExistingUser);

        rootDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users");

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("Username", username);
                hashMap.put("email", email);
                hashMap.put("password", password);

                if (username.isEmpty() || password.isEmpty() || confirm.isEmpty() || email.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in all details", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(confirm)) {
                        if (isValidPassword(password)) {
                            rootDatabaseRef.child(username).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(RegisterActivity.this, "Record Inserted", Toast.LENGTH_SHORT).show();
                                }
                            });
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                        } else {
                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, a letter, a digit, and a special symbol", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password and Confirm password didn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean isValidPassword(String password) {
        int letterCount = 0;
        int digitCount = 0;
        int specialCount = 0;

        if (password.length() < 8) {
            return false;
        } else {
            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);
                if (Character.isLetter(c)) {
                    letterCount++;
                } else if (Character.isDigit(c)) {
                    digitCount++;
                } else if (isSpecialCharacter(c)) {
                    specialCount++;
                }
            }

            return letterCount > 0 && digitCount > 0 && specialCount > 0;
        }
    }

    private static boolean isSpecialCharacter(char c) {
        return (c >= 33 && c <= 46) || c == 64;
    }
}
