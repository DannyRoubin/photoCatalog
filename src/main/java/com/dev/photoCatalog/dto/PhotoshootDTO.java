package com.dev.photoCatalog.dto;

import java.sql.Timestamp;

public class PhotoshootDTO {

    private int photoshootID;
    private Timestamp date;
    private int locationID;  // Reference to the location

    // Constructors
    public PhotoshootDTO() {}

    public PhotoshootDTO(int photoshootID, Timestamp date, int locationID) {
        this.photoshootID = photoshootID;
        this.date = date;
        this.locationID = locationID;
    }

    // Getters and Setters
    public int getPhotoshootID() {
        return photoshootID;
    }

    public void setPhotoshootID(int photoshootID) {
        this.photoshootID = photoshootID;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }
}
