package com.example.task_91p_lost_and_found_app;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemsListActivity extends AppCompatActivity {

    EditText editSearch;
    Spinner spinnerFilter;
    RecyclerView recyclerItems;

    DatabaseHelper databaseHelper;
    ArrayList<Advert> advertList;
    ArrayList<Advert> filteredList;
    AdvertAdapter adapter;

    String selectedCategory = "All";

    String[] filterCategories = {
            "All", "Lost", "Found"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);

        editSearch = findViewById(R.id.editSearch);
        spinnerFilter = findViewById(R.id.spinnerFilter);
        recyclerItems = findViewById(R.id.recyclerItems);

        databaseHelper = new DatabaseHelper(this);

        advertList = new ArrayList<>();
        filteredList = new ArrayList<>();

        recyclerItems.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdvertAdapter(this, filteredList);
        recyclerItems.setAdapter(adapter);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                filterCategories
        );

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFilter.setAdapter(spinnerAdapter);

        loadData();

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterItems();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = filterCategories[position];
                filterItems();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        advertList.clear();
        advertList.addAll(databaseHelper.getAllAdverts());
        filterItems();
    }

    private void filterItems() {
        String searchText = editSearch.getText().toString().toLowerCase().trim();

        filteredList.clear();

        for (Advert advert : advertList) {

            String name = advert.getName() == null ? "" : advert.getName().toLowerCase();
            String description = advert.getDescription() == null ? "" : advert.getDescription().toLowerCase();
            String location = advert.getLocation() == null ? "" : advert.getLocation().toLowerCase();
            String postType = advert.getPostType() == null ? "" : advert.getPostType();

            boolean matchesSearch =
                    name.contains(searchText) ||
                            description.contains(searchText) ||
                            location.contains(searchText) ||
                            postType.toLowerCase().contains(searchText);

            boolean matchesCategory =
                    selectedCategory.equals("All") ||
                            postType.equals(selectedCategory);

            if (matchesSearch && matchesCategory) {
                filteredList.add(advert);
            }
        }

        adapter.notifyDataSetChanged();
    }
}