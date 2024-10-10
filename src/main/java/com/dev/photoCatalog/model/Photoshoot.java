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

    @ManyToOne
    @JoinColumn(name = "locationID", nullable = false)
    private Location locationID;

    // Default constructor
    public Photoshoot() {}

    // Parameterized constructor
    public Photoshoot(Timestamp date) {
        this.date = date;
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

    public Location getLocation() {
        return locationID;
    }

    public void setLocation(Location NewlocationID) {
        this.locationID = NewlocationID;
    }

}
