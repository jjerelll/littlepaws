package com.example.littlepaws;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageButton aboutUsButton = view.findViewById(R.id.aboutus);
        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(requireContext(), AboutUsActivity.class);
                startActivity(i);
            }
        });

        ImageButton mapButton = view.findViewById(R.id.map);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(requireContext(), MapActivity.class);
                startActivity(i);
            }
        });

        ImageButton vetButton = view.findViewById(R.id.vet);
        vetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(requireContext(), VetActivity.class);
                startActivity(i);
            }
        });

        ImageButton abuseButton = view.findViewById(R.id.abuse);
        abuseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(requireContext(), AbuseActivity.class);
                startActivity(i);
            }
        });

        ImageButton donateButton = view.findViewById(R.id.donate);
        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(requireContext(), DonateActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}
