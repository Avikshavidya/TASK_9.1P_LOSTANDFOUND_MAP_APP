package com.example.task_91p_lost_and_found_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnCreateAdvert, btnShowItems, btnShowOnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateAdvert = findViewById(R.id.btnCreateAdvert);
        btnShowItems = findViewById(R.id.btnShowItems);
        btnShowOnMap = findViewById(R.id.btnShowOnMap);

        btnCreateAdvert.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CreateAdvertActivity.class));
        });

        btnShowItems.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ItemsListActivity.class));
        });

        btnShowOnMap.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MapActivity.class));
        });
    }
}