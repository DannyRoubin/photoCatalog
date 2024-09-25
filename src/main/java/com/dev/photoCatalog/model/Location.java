package com.dev.photoCatalog.model;

import jakarta.persistence.*;  

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private int locationID;

    @Column(nullable = false)
    private String locationName;

    // my getters and setters
    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
