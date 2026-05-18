package com.example.task_91p_lost_and_found_app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap googleMap;
    DatabaseHelper databaseHelper;
    ArrayList<Advert> advertList;

    EditText editRadius;
    Button btnSearchRadius;

    FusedLocationProviderClient fusedLocationClient;

    double userLatitude = 0.0;
    double userLongitude = 0.0;

    private static final int LOCATION_PERMISSION_CODE = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        databaseHelper = new DatabaseHelper(this);
        advertList = databaseHelper.getAllAdverts();

        editRadius = findViewById(R.id.editRadius);
        btnSearchRadius = findViewById(R.id.btnSearchRadius);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.mapFragment);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        btnSearchRadius.setOnClickListener(v -> filterByRadius());
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        getUserCurrentLocation();
        showAllItemsOnMap();
    }

    private void getUserCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_CODE
            );
            return;
        }

        googleMap.setMyLocationEnabled(true);

        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        userLatitude = location.getLatitude();
                        userLongitude = location.getLongitude();

                        LatLng userLocation = new LatLng(userLatitude, userLongitude);

                        googleMap.addMarker(new MarkerOptions()
                                .position(userLocation)
                                .title("My Current Location"));

                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 13));

                        Toast.makeText(this, "Current location loaded", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Set emulator location manually", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void showAllItemsOnMap() {
        if (googleMap == null) return;

        for (Advert advert : advertList) {
            LatLng itemLocation = new LatLng(advert.getLatitude(), advert.getLongitude());

            googleMap.addMarker(new MarkerOptions()
                    .position(itemLocation)
                    .title(advert.getPostType() + ": " + advert.getName())
                    .snippet(advert.getDescription()));

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(itemLocation, 10));
        }
    }

    private void filterByRadius() {
        String radiusText = editRadius.getText().toString().trim();

        if (radiusText.isEmpty()) {
            Toast.makeText(this, "Please enter radius in km", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userLatitude == 0.0 && userLongitude == 0.0) {
            Toast.makeText(this, "Current location not available", Toast.LENGTH_SHORT).show();
            return;
        }

        double radiusKm = Double.parseDouble(radiusText);

        googleMap.clear();

        LatLng userLocation = new LatLng(userLatitude, userLongitude);

        googleMap.addMarker(new MarkerOptions()
                .position(userLocation)
                .title("My Current Location"));

        int count = 0;

        for (Advert advert : advertList) {
            float[] results = new float[1];

            Location.distanceBetween(
                    userLatitude,
                    userLongitude,
                    advert.getLatitude(),
                    advert.getLongitude(),
                    results
            );

            double distanceKm = results[0] / 1000.0;

            if (distanceKm <= radiusKm) {
                count++;

                LatLng itemLocation = new LatLng(advert.getLatitude(), advert.getLongitude());

                googleMap.addMarker(new MarkerOptions()
                        .position(itemLocation)
                        .title(advert.getPostType() + ": " + advert.getName())
                        .snippet("Distance: " + String.format("%.2f", distanceKm) + " km"));
            }
        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 13));

        Toast.makeText(
                this,
                count + " item(s) found within " + radiusKm + " km",
                Toast.LENGTH_SHORT
        ).show();
    }
}