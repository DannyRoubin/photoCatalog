package com.dev.photoCatalog.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate photoID
    private int photoID;

    @Column(nullable = false, unique = true)
    private UUID photoGUID;  // UUID is manually generated, no need for @GeneratedValue

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private java.sql.Timestamp timeStamp;

    // Default constructor
    public Photo() {
        this.photoGUID = UUID.randomUUID();  // Manually generate UUID
    }

    // Parameterized constructor
    public Photo(String fileName, java.sql.Timestamp timeStamp) {
        this.photoGUID = UUID.randomUUID();
        this.fileName = fileName;
        this.timeStamp = timeStamp;
    }

    // Getters and Setters
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
