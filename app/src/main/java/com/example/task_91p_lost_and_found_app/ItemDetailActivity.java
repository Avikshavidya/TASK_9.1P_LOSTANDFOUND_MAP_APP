package com.example.task_91p_lost_and_found_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailActivity extends AppCompatActivity {

    TextView textPostType, textName, textPhone, textDescription, textDate, textLocation;
    Button btnRemove;

    DatabaseHelper databaseHelper;
    int advertId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        textPostType = findViewById(R.id.textPostType);
        textName = findViewById(R.id.textName);
        textPhone = findViewById(R.id.textPhone);
        textDescription = findViewById(R.id.textDescription);
        textDate = findViewById(R.id.textDate);
        textLocation = findViewById(R.id.textLocation);
        btnRemove = findViewById(R.id.btnRemove);

        databaseHelper = new DatabaseHelper(this);

        advertId = getIntent().getIntExtra("advertId", -1);

        if (advertId != -1) {
            loadAdvertDetails();
        }

        btnRemove.setOnClickListener(v -> {
            boolean deleted = databaseHelper.deleteAdvert(advertId);

            if (deleted) {
                Toast.makeText(this, "Item removed successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to remove item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadAdvertDetails() {
        Advert advert = databaseHelper.getAdvertById(advertId);

        if (advert != null) {
            textPostType.setText("Post Type: " + advert.getPostType());
            textName.setText("Name: " + advert.getName());
            textPhone.setText("Phone: " + advert.getPhone());
            textDescription.setText("Description: " + advert.getDescription());
            textDate.setText("Date: " + advert.getDate());
            textLocation.setText("Location: " + advert.getLocation());
        }
    }
}