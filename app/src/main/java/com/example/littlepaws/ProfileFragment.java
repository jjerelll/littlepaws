package com.example.littlepaws;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    private TextView nameTextView;
    private TextView emailTextView;
    private TextView phoneTextView;
    private EditText bioEditText;
    private EditText birthdayEditText;
    private EditText genderEditText;
    private EditText addressEditText;
    private Button editButton;
    private Button saveButton;
    private Button logoutButton; // Add logout button

    private boolean isEditMode = false;
    private String email;
    private String name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        nameTextView = view.findViewById(R.id.profilename);
        emailTextView = view.findViewById(R.id.profileemail);
        phoneTextView = view.findViewById(R.id.profilenum);
        bioEditText = view.findViewById(R.id.bioEditText);
        birthdayEditText = view.findViewById(R.id.birthdayEditText);
        genderEditText = view.findViewById(R.id.genderEditText);
        addressEditText = view.findViewById(R.id.addressEditText);
        editButton = view.findViewById(R.id.editButton);
        saveButton = view.findViewById(R.id.saveButton);
        logoutButton = view.findViewById(R.id.logoutButton); // Initialize logout button

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableEditMode();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
                disableEditMode();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        // Set initial values for the fields
        bioEditText.setText("");
        birthdayEditText.setText("");
        genderEditText.setText("");
        addressEditText.setText("");

        // Disable the EditText fields and hide the save button initially
        disableEditMode();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Retrieve the email and name values from the arguments
        Bundle arguments = getArguments();
        if (arguments != null) {
            email = arguments.getString("email");
            name = arguments.getString("name");
        }

        // Set the retrieved values to the respective TextViews
        emailTextView.setText(email);
        nameTextView.setText(name);
    }

    private void enableEditMode() {
        isEditMode = true;
        editButton.setVisibility(View.GONE);
        saveButton.setVisibility(View.VISIBLE);

        // Enable and show the EditText fields for editing
        bioEditText.setEnabled(true);
        birthdayEditText.setEnabled(true);
        genderEditText.setEnabled(true);
        addressEditText.setEnabled(true);

        // Set the focus on the first editable field
        bioEditText.requestFocus();
    }

    private void disableEditMode() {
        isEditMode = false;
        editButton.setVisibility(View.VISIBLE);
        saveButton.setVisibility(View.GONE);

        // Disable and hide the EditText fields
        bioEditText.setEnabled(false);
        bioEditText.setVisibility(View.VISIBLE);

        birthdayEditText.setEnabled(false);
        birthdayEditText.setVisibility(View.VISIBLE);

        genderEditText.setEnabled(false);
        genderEditText.setVisibility(View.VISIBLE);

        addressEditText.setEnabled(false);
        addressEditText.setVisibility(View.VISIBLE);

        // Show the updated values in the TextView fields
        nameTextView.setVisibility(View.VISIBLE);
        emailTextView.setVisibility(View.VISIBLE);
        phoneTextView.setVisibility(View.VISIBLE);
    }

    private void saveChanges() {
        // Get the new values from the EditText fields and save them to your model or database
        String newBio = bioEditText.getText().toString();
        String newBirthday = birthdayEditText.getText().toString();
        String newGender = genderEditText.getText().toString();
        String newAddress = addressEditText.getText().toString();

        // Update the TextView fields with the new values
        nameTextView.setVisibility(View.VISIBLE);
        emailTextView.setVisibility(View.VISIBLE);
        phoneTextView.setVisibility(View.VISIBLE);
        bioEditText.setVisibility(View.GONE);
        birthdayEditText.setVisibility(View.GONE);
        genderEditText.setVisibility(View.GONE);
        addressEditText.setVisibility(View.GONE);

        // You can save the new values to your model or database here
    }

    private void logout() {
        // Perform the logout operation here
        // For example, you can clear the session or user data

        // Start the Login activity
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish(); // Optional: finish the current activity to prevent going back

        // Display a Toast message
        Toast.makeText(getActivity(), "LOGOUT SUCCESS", Toast.LENGTH_SHORT).show();
    }
}
