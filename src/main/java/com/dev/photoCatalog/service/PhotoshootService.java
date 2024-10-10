package com.dev.photoCatalog.service;

import com.dev.photoCatalog.dto.PhotoshootDTO;
import com.dev.photoCatalog.model.Photoshoot;
import com.dev.photoCatalog.model.Photo;
import com.dev.photoCatalog.model.Location;
import com.dev.photoCatalog.repository.PhotoshootRepository;
import com.dev.photoCatalog.repository.PhotoRepository;
import com.dev.photoCatalog.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import jakarta.transaction.Transactional;

import java.util.UUID;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoshootService {

    @Autowired
    private PhotoshootRepository photoshootRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Get all photoshoots
    public List<Photoshoot> getAllPhotoshoots() {
        return photoshootRepository.findAll();
    }

    // Get a specific photoshoot by ID
    public Photoshoot getPhotoshootById(int id) {
        Optional<Photoshoot> optionalPhotoshoot = photoshootRepository.findById(id);
        return optionalPhotoshoot.orElse(null); // Return the photoshoot if found, or null
    }

    // Add a new photoshoot
    public Photoshoot addPhotoshoot(Photoshoot photoshoot) {
        return photoshootRepository.save(photoshoot);
    }

    // Update an existing photoshoot
    // src/main/java/com/dev/photoCatalog/service/PhotoshootService.java

    public Photoshoot updatePhotoshoot(int id, Photoshoot updatedPhotoshoot) {
        Optional<Photoshoot> existingPhotoshoot = photoshootRepository.findById(id);
        if (existingPhotoshoot.isPresent()) {
            Photoshoot photoshoot = existingPhotoshoot.get();
            photoshoot.setDate(updatedPhotoshoot.getDate());

            // Fetch and set the new location
            Location newLocation = locationRepository.findById(updatedPhotoshoot.getLocation().getLocationID())
                    .orElseThrow(() -> new IllegalArgumentException("Location not found"));
            photoshoot.setLocation(newLocation);

            return photoshootRepository.save(photoshoot);
        } else {
            return null;
        }
    }

    // Delete a photoshoot by ID
    public void deletePhotoshoot(int id) {
        photoshootRepository.deleteById(id);
    }

    // Get all photos for a specific photoshoot by querying the junction table and retrieving photos by photoGUID
    public List<Photo> getAllPhotosForPhotoshoot(int photoshootId) {
        return photoRepository.findAllPhotosForPhotoshoot(photoshootId); // Implement repository logic for this
    }

    // Get all locations for a specific photoshoot by querying the junction table and retrieving locations
    public List<Location> getAllLocationsForPhotoshoot(int photoshootId) {
        // Assuming we are using a query to fetch all locations associated with a photoshoot
        return locationRepository.findAllLocationsForPhotoshoot(photoshootId); // Implement repository logic for this
    }

    @Transactional
    public void addPhotoToPhotoshoot(int photoshootID, UUID photoGUID) {
        // Fetch the photo and photoshoot entities first
        Photoshoot photoshoot = photoshootRepository.findById(photoshootID)
                .orElseThrow(() -> new IllegalArgumentException("Photoshoot not found"));

        Photo photo = photoRepository.findByPhotoGUID(photoGUID.toString())
                .orElseThrow(() -> new IllegalArgumentException("Photo not found"));

        // Update the SQL query to use PhotoGUID instead of PhotoID
        String sql = "INSERT INTO PhotoshootPhotoJunction (photoshootID, photoGUID) VALUES (?, ?)";
        jdbcTemplate.update(sql, photoshoot.getPhotoshootID(), photo.getPhotoGUID());
    }

}
