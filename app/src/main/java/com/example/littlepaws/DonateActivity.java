package com.example.littlepaws;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class DonateActivity extends AppCompatActivity {
    private DatabaseReference donationsRef;
    private EditText amountEditText;
    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donateact);

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        donationsRef = database.getReference("donations");

        // Get references to the EditText fields
        amountEditText = findViewById(R.id.etamount);
        nameEditText = findViewById(R.id.etname);

        Button donateButton = findViewById(R.id.btnDonate);
        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the donation amount and donor name from EditText fields
                String amount = amountEditText.getText().toString();
                String name = nameEditText.getText().toString();

                // Format the donation amount to Philippine currency
                double donationAmount = Double.parseDouble(amount);
                DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.getDefault());
                decimalFormat.setCurrency(Currency.getInstance("PHP"));
                String formattedAmount = decimalFormat.format(donationAmount);

                // Create a new Donation object with the formatted amount and name
                Donation donation = new Donation(formattedAmount, name);

                // Generate a unique key for the donation
                String donationKey = donationsRef.push().getKey();

                // Save the donation to the database using the generated key
                donationsRef.child(donationKey).setValue(donation);

                // Show a custom dialog as a floating window notification
                showNotificationDialog();

                // Reset the EditText fields
                resetFields();
            }
        });
    }

    private void resetFields() {
        amountEditText.setText("");
        nameEditText.setText("");
    }

    public static class Donation {
        private String amount;
        private String name;

        public Donation() {
            // Default constructor required for Firebase
        }

        public Donation(String amount, String name) {
            this.amount = amount;
            this.name = name;
        }

        public String getAmount() {
            return amount;
        }

        public String getName() {
            return name;
        }
    }

    private void showNotificationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Donation Successful!")
                .setMessage("Thank you for your donation!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }
}
