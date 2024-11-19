package com.example.dokyudashprototype;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.content.Intent;
import android.net.Uri;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap gMap;
    private Button toggleButton;
    private boolean isFullscreen = false;

    private String title;
    private double latitude;
    private double longitude;
    private int logo;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_activity);

        // Get the passed values from the Intent
        title = getIntent().getStringExtra("name");
        latitude = getIntent().getDoubleExtra("latitude", 0);
        longitude = getIntent().getDoubleExtra("longitude", 0);
        logo = getIntent().getIntExtra("logo", -1);

        // Initialize the map fragment
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        // Initialize the toggle fullscreen button
        toggleButton = findViewById(R.id.toggle_fullscreen_button);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFullscreen();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        // Use the passed latitude and longitude to create a LatLng object
        LatLng location = new LatLng(latitude, longitude);
        gMap.addMarker(new MarkerOptions().position(location).title(title));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 17));
    }

    private void toggleFullscreen() {
        if (!isFullscreen) {
            // Dynamically create a new container for the fullscreen map
            FrameLayout fullscreenContainer = new FrameLayout(this);
            fullscreenContainer.setId(View.generateViewId());
            fullscreenContainer.setLayoutParams(new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            ));


            // Add a button to open Google Maps
            Button openMapsButton = new Button(this);
            openMapsButton.setText("Open in Google Maps");
            FrameLayout.LayoutParams openMapsButtonParams = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
            );
            openMapsButtonParams.setMargins(16, 20, 0, 0); // Adjust position
            openMapsButton.setLayoutParams(openMapsButtonParams);
            openMapsButton.setOnClickListener(v -> openGoogleMaps());
            fullscreenContainer.addView(openMapsButton);

            // Add a new SupportMapFragment
            SupportMapFragment newMapFragment = SupportMapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(fullscreenContainer.getId(), newMapFragment).commit();

            // Add the fullscreen container to the root layout
            ViewGroup rootLayout = findViewById(android.R.id.content);
            rootLayout.addView(fullscreenContainer);

            // Initialize the new map
            newMapFragment.getMapAsync(googleMap -> {
                LatLng location = new LatLng(latitude, longitude);
                googleMap.addMarker(new MarkerOptions().position(location).title(title));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 17));
            });

            // Set fullscreen flag
            isFullscreen = true;
        }
    }

    private void exitFullscreen(FrameLayout fullscreenContainer) {
        // Remove the fullscreen container from the root layout
        ViewGroup rootLayout = findViewById(android.R.id.content);
        rootLayout.removeView(fullscreenContainer);

        // Reset fullscreen flag
        isFullscreen = false;
    }

    private void openGoogleMaps() {
        // Open Google Maps app using an intent
        String uri = "geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude + "(" + title + ")";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps"); // Ensure it opens in Google Maps app

        // Check if Google Maps is installed
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Google Maps not installed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (isFullscreen) {
            // Exit fullscreen by removing the fullscreen container
            ViewGroup rootLayout = findViewById(android.R.id.content);
            if (rootLayout.getChildCount() > 1) {
                rootLayout.removeViewAt(rootLayout.getChildCount() - 1);
                isFullscreen = false;
            }
        } else {
            super.onBackPressed();
        }
    }
}
