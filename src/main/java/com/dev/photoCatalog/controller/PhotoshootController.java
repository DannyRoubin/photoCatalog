package com.dev.photoCatalog.controller;

import com.dev.photoCatalog.model.Photoshoot;
import com.dev.photoCatalog.model.Photo;
import com.dev.photoCatalog.model.Location;
import com.dev.photoCatalog.service.PhotoshootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/photoshoot") // Use singular for the entity
public class PhotoshootController {

    @Autowired
    private PhotoshootService photoshootService;

    // Get all photoshoots
    @GetMapping
    public List<Photoshoot> getAllPhotoshoots() {
        return photoshootService.getAllPhotoshoots();
    }

    // Get a specific photoshoot by ID
    @GetMapping("/{id}")
    public Photoshoot getPhotoshootById(@PathVariable int id) {
        return photoshootService.getPhotoshootById(id);
    }

    // Add a new photoshoot
    @PostMapping
    public Photoshoot addPhotoshoot(@RequestBody Photoshoot photoshoot) {
        return photoshootService.addPhotoshoot(photoshoot);
    }

    // Update an existing photoshoot
    @PutMapping("/{id}")
    public Photoshoot updatePhotoshoot(@PathVariable int id, @RequestBody Photoshoot photoshoot) {
        return photoshootService.updatePhotoshoot(id, photoshoot);
    }

    // Delete a photoshoot by ID
    @DeleteMapping("/{id}")
    public String deletePhotoshoot(@PathVariable int id) {
        photoshootService.deletePhotoshoot(id);
        return "Photoshoot deleted successfully.";
    }

    // Get all photos for a specific photoshoot
    @GetMapping("/{photoshootId}/photo")
    public ResponseEntity<List<Photo>> getAllPhotosForPhotoshoot(@PathVariable int photoshootId) {
        List<Photo> photos = photoshootService.getAllPhotosForPhotoshoot(photoshootId);
        return ResponseEntity.ok(photos);
    }

    //  Get all locations for a specific photoshoot
    @GetMapping("/{photoshootId}/location")
    public ResponseEntity<List<Location>> getAllLocationsForPhotoshoot(@PathVariable int photoshootId) {
        List<Location> locations = photoshootService.getAllLocationsForPhotoshoot(photoshootId);
        return ResponseEntity.ok(locations);
    }

    @PostMapping("/{photoshootID}/addPhoto/{photoGUID}")
    public ResponseEntity<String> addPhotoToPhotoshoot(@PathVariable int photoshootID, @PathVariable UUID photoGUID) {
        photoshootService.addPhotoToPhotoshoot(photoshootID, photoGUID);
        return ResponseEntity.ok("Photo added to Photoshoot successfully!");
    }
}
