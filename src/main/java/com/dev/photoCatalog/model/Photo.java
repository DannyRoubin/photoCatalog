package com.dev.photoCatalog.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate photoID
    private int photoID;

    @Schema(type = "string", format = "uuid", description = "Unique identifier for the photo", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(columnDefinition = "org.hibernate.type.UUIDCharType", nullable = false, unique = true)
    private String photoGUID;

    @Column(nullable = false, length = 256)  // Specifying length to match database constraint
    private String fileName;

    @Column(nullable = false)
    private Timestamp timeStamp;

    // constructors
    public Photo() {
        // Remove UUID generation from here
    }

    public Photo(String fileName, Timestamp timeStamp) {
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

    public String getPhotoGUID() {
        return photoGUID;
    }

    public void setPhotoGUID(String photoGUID) {
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