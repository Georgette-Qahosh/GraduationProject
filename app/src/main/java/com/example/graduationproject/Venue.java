package com.example.graduationproject;

import com.google.firebase.firestore.GeoPoint;

public class Venue {
    String city , name , picture ,vendorName;
    long price;
    GeoPoint map;
    Boolean isBooked;

    public Venue(){

    }

    public Venue(String city, String name, String picture, long price, GeoPoint map, Boolean isBooked) {
        this.city = city;
        this.name = name;
        this.vendorName = vendorName;
        this.picture = picture;
        this.price = price;
        this.map = map;
        this.isBooked = isBooked;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public GeoPoint getMap() {
        return map;
    }

    public void setMap(GeoPoint map) {
        this.map = map;
    }

    public Boolean getBooked() {
        return isBooked;
    }

    public void setBooked(Boolean booked) {
        isBooked = booked;
    }
}
