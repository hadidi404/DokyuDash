package com.example.dokyudashprototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap gMap;
    private Button toggleButton;
    private boolean isFullscreen = false;

    private int originalHeight = 280;
    private int originalMarginTop = 177;
    private int originalMarginBottom = 274;
    private TextView agencyPageTitle;

    // Declare variables for the passed data
    private String title;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_activity);

        // Get the passed values from the Intent
        title = getIntent().getStringExtra("name");
        latitude = getIntent().getDoubleExtra("latitude", 0);
        longitude = getIntent().getDoubleExtra("longitude", 0);

        agencyPageTitle = findViewById(R.id.AgencyPageTitle);

        if (title != null) {
            agencyPageTitle.setText(title);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        toggleButton = findViewById(R.id.toggle_fullscreen_button);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeFullscreenMap();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        // Use the passed latitude and longitude to create a LatLng
        LatLng location = new LatLng(latitude, longitude);
        gMap.addMarker(new MarkerOptions().position(location).title(title));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 17), 2000, null);
    }

    private void makeFullscreenMap() {
        View mapFragment = findViewById(R.id.id_map);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mapFragment.getLayoutParams();

        if (!isFullscreen) {
            originalHeight = params.height;
            originalMarginTop = params.topMargin;
            originalMarginBottom = params.bottomMargin;

            agencyPageTitle.setVisibility(View.GONE);

            params.height = RelativeLayout.LayoutParams.MATCH_PARENT;
            params.setMargins(0, 0, 0, 0);  // Remove margins
            mapFragment.setLayoutParams(params);
            toggleButton.setVisibility(View.GONE);  // Hide the button
            isFullscreen = true;
        }
    }

    @Override
    public void onBackPressed() {
        if (isFullscreen) {
            View mapFragment = findViewById(R.id.id_map);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mapFragment.getLayoutParams();

            params.height = originalHeight;
            params.setMargins(0, originalMarginTop, 0, originalMarginBottom);
            mapFragment.setLayoutParams(params);

            agencyPageTitle.setVisibility(View.VISIBLE);
            toggleButton.setVisibility(View.VISIBLE);
            isFullscreen = false;
        } else {
            super.onBackPressed();
        }
    }
}