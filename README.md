# TASK_9.1P_LOST_AND_FOUND_APP

## Student Information

- Name: Aviksha Vidya Koundinya
- Unit: SIT708 Mobile Systems Development
- Task: 9.1P – Lost and Found Map Mobile App

---

# Project Overview

This project is a Lost and Found mobile application developed using Android Studio and Java. The purpose of the application is to help users create lost or found item adverts and locate nearby items using Google Maps and location services.

The application allows users to:
- Create lost or found adverts
- Save item details into a SQLite database
- Use the device current location
- Select locations using Google Places Autocomplete
- Display all adverts on Google Maps
- Search for nearby items using radius-based filtering

---

# Features Implemented

## 1. Create Advert

Users can create a lost or found advert by entering:
- Post type (Lost or Found)
- Item name
- Phone number
- Description
- Date
- Location

The advert is saved into the SQLite database.

---

## 2. Current Location

The application uses FusedLocationProviderClient to retrieve the user’s current location. The latitude and longitude are stored together with the advert information.

---

## 3. Google Places Autocomplete

Users can search and select locations using Google Places Autocomplete API.

---

## 4. SQLite Database

SQLite database is used to:
- Store advert information
- Save latitude and longitude values
- Retrieve all saved adverts
- Delete adverts

---

## 5. RecyclerView Item List

All saved adverts are displayed using RecyclerView. Users can:
- Search adverts
- Filter lost/found items
- Open advert details

---

## 6. Google Maps Integration

All saved adverts are displayed as markers on Google Maps.

Each marker shows:
- Item type
- Item name
- Description

---

## 7. Radius-Based Search

Users can enter a radius value in kilometres to search nearby items.

The application compares:
- User current location
- Advert location coordinates

using Android’s `Location.distanceBetween()` method.

Only nearby items are displayed on the map.

---

# Technologies Used

- Java
- Android Studio
- SQLite Database
- Google Maps API
- Google Places API
- RecyclerView
- FusedLocationProviderClient

---

# Project Structure

## Activities

- MainActivity
- CreateAdvertActivity
- ItemsListActivity
- ItemDetailActivity
- MapActivity

## Helper Classes

- DatabaseHelper
- Advert
- AdvertAdapter

---

# How to Run the Application

1. Open the project in Android Studio
2. Add your Google Maps API key inside `AndroidManifest.xml`
3. Sync Gradle files
4. Run the application using an emulator or Android device

---

# Permissions Used

The application uses:
- Internet permission
- Fine location permission
- Coarse location permission

---

# Challenges Faced

Some challenges faced during development included:
- Configuring Google Maps API correctly
- Handling runtime location permissions
- Managing RecyclerView updates
- Implementing radius-based filtering logic

These issues were solved through debugging and testing in Android Studio.

---

# What I Learned

Through this project, I learned:
- Android activity navigation
- SQLite database integration
- RecyclerView implementation
- Google Maps integration
- Runtime permission handling
- Location-based mobile application development

---


All code was reviewed, tested, and modified according to the project requirements.
