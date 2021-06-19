package com.example.graduationproject;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.GeoPoint;

public class FeaturedVenue {
    String city , name , vendorName , idV ;
    Long price;
    GeoPoint map;
    Boolean isBooked;
    DocumentReference venueDoc;

    public FeaturedVenue(){


    }


    public FeaturedVenue(String city, String name, Long price, GeoPoint map, Boolean isBooked) {
        this.city = city;
        this.name = name;
        this.idV = idV;
        this.venueDoc = venueDoc;
        this.vendorName = vendorName;
        this.price = price;
        this.map = map;
        this.isBooked = isBooked;
    }

    public String getIdV() {
        return idV;
    }

    public void setIdV(String idV) {
        this.idV = idV;
    }

    public DocumentReference getVenueDoc() {
        return venueDoc;
    }

    public void setVenueDoc(DocumentReference venueDoc) {
        this.venueDoc = venueDoc;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
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
