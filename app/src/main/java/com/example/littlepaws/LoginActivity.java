package com.example.littlepaws;

import android.annotation.SuppressLint;
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

public class LoginActivity extends AppCompatActivity {

    EditText Email;
    EditText Password;
    Button LoginBtn;
    Button CreateBtn;

    FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        LoginBtn = findViewById(R.id.loginbtn);
        CreateBtn = findViewById(R.id.createnow);

        mAuth = FirebaseAuth.getInstance();

        LoginBtn.setOnClickListener(view -> {
            loginUser();
        });
        CreateBtn.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class));
        });
    }

    private void loginUser() {
        String email = Email.getText().toString();
        String password = Password.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Email.setError("Email cannot be empty");
            Email.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            Password.setError("Password cannot be empty");
            Password.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}