package com.dev.photoCatalog.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class Photoshoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer photoshootID;

    @Column(nullable = false)
    private Timestamp date;

    @Column(name = "locationID", nullable = true)
    private Integer locationID;  // Storing locationID as Integer

    // Default constructor
    public Photoshoot() {}

    // Parameterized constructor
    public Photoshoot(Timestamp date, Integer locationID) {
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
