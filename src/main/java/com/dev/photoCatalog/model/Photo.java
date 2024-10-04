package com.dev.photoCatalog.model;

import jakarta.persistence.*;
import java.util.UUID;
import java.sql.Timestamp;

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate photoID
    private int photoID;

    @Column(nullable = false, unique = true)
    private UUID photoGUID;  // UUID is manually generated, no need for @GeneratedValue

    @Column(nullable = false, length = 256)  // Specifying length to match database constraint
    private String fileName;

    @Column(nullable = false)
    private Timestamp timeStamp;

    // constructors
    public Photo() {
        this.photoGUID = UUID.randomUUID();  // Manually generate UUID
    }

    public Photo(String fileName, Timestamp timeStamp) {
        this.photoGUID = UUID.randomUUID();
        this.fileName = fileName;
        this.timeStamp = timeStamp;
    }

    // getters and setters
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

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}
