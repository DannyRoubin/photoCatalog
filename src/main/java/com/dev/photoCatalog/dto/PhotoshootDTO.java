package com.dev.photoCatalog.dto;

import java.sql.Timestamp;

public class PhotoshootDTO {

    private Integer photoshootID;
    private Timestamp date;
    private Integer locationID;

    // Default constructor
    public PhotoshootDTO() {}

    // Parameterized constructor
    public PhotoshootDTO(Integer photoshootID, Timestamp date, Integer locationID) {
        this.photoshootID = photoshootID;
        this.date = date;
        this.locationID = locationID;
    }

    // Getters and Setters
    public Integer getPhotoshootID() {
        return photoshootID;
    }

    public void setPhotoshootID(Integer photoshootID) {
        this.photoshootID = photoshootID;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getLocationID() {
        return locationID;
    }

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }
}
