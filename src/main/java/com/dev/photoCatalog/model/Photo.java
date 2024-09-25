package com.dev.photoCatalog.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private int photoID;

    @Column(nullable = false, unique = true)
    private UUID photoGUID;  

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private java.sql.Timestamp timeStamp;

    // my getters and setters for photo
    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public UUID getPhotoGUID() {
        return photoGUID;
    }

    public void setPhotoGUID(UUID photoGUID) {
        this.photoGUID = photoGUID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public java.sql.Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(java.sql.Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}
