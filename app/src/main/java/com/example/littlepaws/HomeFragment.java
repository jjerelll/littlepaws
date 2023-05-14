package com.example.littlepaws;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.littlepaws.R;

public class HomeFragment extends Fragment {

    ImageButton imgButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Get a reference to the ImageButton
        ImageButton aboutUsButton = view.findViewById(R.id.aboutus);

        // Set OnClickListener for the ImageButton
        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the AboutUs activity
                startActivity(new Intent(getActivity(), AboutUs.class));
            }
        });

        return view;
    }
}
