package com.example.task_91p_lost_and_found_app;

public class Advert {

    private int id;
    private String postType, name, phone, description, date, location;
    private double latitude, longitude;

    public Advert(int id, String postType, String name, String phone,
                  String description, String date, String location,
                  double latitude, double longitude) {
        this.id = id;
        this.postType = postType;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() { return id; }
    public String getPostType() { return postType; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getLocation() { return location; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}