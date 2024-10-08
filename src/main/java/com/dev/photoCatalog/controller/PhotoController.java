package com.dev.photoCatalog.controller;

import com.dev.photoCatalog.model.Photo;
import com.dev.photoCatalog.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    // Get all photos
    @GetMapping
    public List<Photo> getAllPhotos() {
        return photoService.getAllPhotos();
    }

    // Get a specific photo by ID
    @GetMapping("/{id}")
    public Photo getPhotoById(@PathVariable int id) {
        return photoService.getPhotoById(id);
    }

    // Add a new photo
    @PostMapping
    public Photo addPhoto(@RequestBody Photo photo) {
        System.out.println("Before saving: " + photo.getPhotoGUID());  // Log the GUID before saving
        Photo savedPhoto = photoService.addPhoto(photo);
        System.out.println("After saving: " + savedPhoto.getPhotoGUID());  // Log the GUID after saving
        return savedPhoto;  // Return the saved photo (with the GUID)
    }

    // Update an existing photo
    @PutMapping("/{id}")
    public Photo updatePhoto(@PathVariable int id, @RequestBody Photo photo) {
        return photoService.updatePhoto(id, photo);
    }

    // Delete a photo by ID
    @DeleteMapping("/{id}")
    public String deletePhoto(@PathVariable int id) {
        photoService.deletePhoto(id);
        return "Photo deleted successfully.";
    }

    // Get a specific photo by GUID
    @GetMapping("/guid/{photoGUID}")
    public Photo getPhotoByGUID(@PathVariable String photoGUID) {
        return photoService.getPhotoByGUID(photoGUID);
    }
}