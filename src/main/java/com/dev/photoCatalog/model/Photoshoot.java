package com.dev.photoCatalog.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class Photoshoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int photoshootID;

    //photoIDs will be comma seperated
    @Column(nullable = false)
    private String photoIDs;  

    //locationIDs will be comma seperated as well
    @Column(nullable = false)
    private String locationIDs;  

    @Column(nullable = true)
    private Timestamp date;

    // getters and setters for photoshoot
    public int getPhotoshootID() {
        return photoshootID;
    }

    public void setPhotoshootID(int photoshootID) {
        this.photoshootID = photoshootID;
    }

    public String getPhotoIDs() {
        return photoIDs;
    }

    public void setPhotoIDs(String photoIDs) {
        this.photoIDs = photoIDs;
    }

    public String getLocationIDs() {
        return locationIDs;
    }

    public void setLocationIDs(String locationIDs) {
        this.locationIDs = locationIDs;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    //method to get PhotoID list as an array
    public int[] getPhotoIDArray() {
        String[] idStrings = this.photoIDs.split(",");
        int[] ids = new int[idStrings.length];
        for (int i = 0; i < idStrings.length; i++) {
            ids[i] = Integer.parseInt(idStrings[i].trim());
        }
        return ids;
    }

    //method to get LocationID list as an array
    public int[] getLocationIDArray() {
        String[] idStrings = this.locationIDs.split(",");
        int[] ids = new int[idStrings.length];
        for (int i = 0; i < idStrings.length; i++) {
            ids[i] = Integer.parseInt(idStrings[i].trim());
        }
        return ids;
    }
}
