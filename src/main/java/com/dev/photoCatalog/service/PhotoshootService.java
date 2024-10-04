package com.dev.photoCatalog.service;

import com.dev.photoCatalog.model.Photoshoot;
import com.dev.photoCatalog.model.Photo;
import com.dev.photoCatalog.model.Location;
import com.dev.photoCatalog.repository.PhotoshootRepository;
import com.dev.photoCatalog.repository.PhotoRepository;
import com.dev.photoCatalog.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Photoshoot updatePhotoshoot(int id, Photoshoot updatedPhotoshoot) {
        Optional<Photoshoot> existingPhotoshoot = photoshootRepository.findById(id);
        if (existingPhotoshoot.isPresent()) {
            Photoshoot photoshoot = existingPhotoshoot.get();
            photoshoot.setDate(updatedPhotoshoot.getDate());
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
        // Assuming we are using a query to fetch all photos associated with a photoshoot
        // This should be done by joining the junction table and finding photos by their GUID
        return photoRepository.findAllPhotosForPhotoshoot(photoshootId); // Implement repository logic for this
    }

    // Get all locations for a specific photoshoot by querying the junction table and retrieving locations
    public List<Location> getAllLocationsForPhotoshoot(int photoshootId) {
        // Assuming we are using a query to fetch all locations associated with a photoshoot
        return locationRepository.findAllLocationsForPhotoshoot(photoshootId); // Implement repository logic for this
    }
}
