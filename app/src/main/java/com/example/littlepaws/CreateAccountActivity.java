package com.example.littlepaws;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountActivity extends AppCompatActivity {

    EditText c_email;
    EditText c_password;
    Button LoginHere;
    Button create_account_btn;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        c_email = findViewById(R.id.c_email);
        c_password = findViewById(R.id.c_password);
        LoginHere = findViewById(R.id.LoginHere);
        create_account_btn = findViewById(R.id.create_account_btn);

        mAuth = FirebaseAuth.getInstance();

        create_account_btn.setOnClickListener(view -> {
            createUser();
        });

        LoginHere.setOnClickListener(view -> {
            startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class));
        });
    }

    private void createUser() {
        String email = c_email.getText().toString();
        String password = c_password.getText().toString();

        if (TextUtils.isEmpty(email)) {
            c_email.setError("Email cannot be empty");
            c_email.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            c_password.setError("Password cannot be empty");
            c_password.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(CreateAccountActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class));
                    } else {
                        Toast.makeText(CreateAccountActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}