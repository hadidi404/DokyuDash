package com.example.dokyudashprototype;

public class Recycling {
    private String id;
    private String name;
    private int imageResourceId;
    private String material;
    private double latitude;
    private double longitude;

    public Recycling(String id, String name, int imageResourceId, String material, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.material = material;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }
    public int getImage() {
        return imageResourceId;
    }
    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

