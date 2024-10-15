package com.dev.photoCatalog.controller;

import com.dev.photoCatalog.dto.PhotoshootDTO;
import com.dev.photoCatalog.model.Photo;
import com.dev.photoCatalog.service.PhotoshootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/photoshoot") // Keep the singular naming for the entity
public class PhotoshootController {

    @Autowired
    private PhotoshootService photoshootService;

    // Get all photoshoots as DTOs
    @GetMapping
    public ResponseEntity<List<PhotoshootDTO>> getAllPhotoshoots() {
        List<PhotoshootDTO> photoshoots = photoshootService.getAllPhotoshoots();
        return ResponseEntity.ok(photoshoots);
    }

    // Get a specific photoshoot by ID as a DTO
    @GetMapping("/{id}")
    public ResponseEntity<PhotoshootDTO> getPhotoshootById(@PathVariable int id) {
        Optional<PhotoshootDTO> photoshootDTO = photoshootService.getPhotoshootById(id);
        return photoshootDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add a new photoshoot
    @PostMapping
    public ResponseEntity<PhotoshootDTO> addPhotoshoot(@RequestBody PhotoshootDTO photoshootDTO) {
        PhotoshootDTO createdPhotoshoot = photoshootService.addPhotoshoot(photoshootDTO);
        return ResponseEntity.ok(createdPhotoshoot);
    }

    // Update an existing photoshoot
    @PutMapping("/{id}")
    public ResponseEntity<PhotoshootDTO> updatePhotoshoot(@PathVariable int id, @RequestBody PhotoshootDTO photoshootDTO) {
        Optional<PhotoshootDTO> updatedPhotoshoot = photoshootService.updatePhotoshoot(id, photoshootDTO);
        return updatedPhotoshoot.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a photoshoot by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePhotoshoot(@PathVariable int id) {
        photoshootService.deletePhotoshoot(id);
        return ResponseEntity.ok("Photoshoot deleted successfully.");
    }

    // Get all photos for a specific photoshoot
    @GetMapping("/{photoshootId}/photo")
    public ResponseEntity<List<Photo>> getAllPhotosForPhotoshoot(@PathVariable int photoshootId) {
        List<Photo> photos = photoshootService.getAllPhotosForPhotoshoot(photoshootId);
        return ResponseEntity.ok(photos);
    }

    // Add a photo to a photoshoot using photoGUID
    @PostMapping("/{photoshootID}/addPhoto/{photoGUID}")
    public ResponseEntity<String> addPhotoToPhotoshoot(@PathVariable int photoshootID, @PathVariable UUID photoGUID) {
        photoshootService.addPhotoToPhotoshoot(photoshootID, photoGUID);
        return ResponseEntity.ok("Photo added to Photoshoot successfully!");
    }
}
