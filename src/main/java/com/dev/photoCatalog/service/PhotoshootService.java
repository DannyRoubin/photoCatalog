package com.dev.photoCatalog.service;

import com.dev.photoCatalog.model.Photo;
import com.dev.photoCatalog.model.Location;
import com.dev.photoCatalog.model.Photoshoot;
import com.dev.photoCatalog.repository.PhotoRepository;
import com.dev.photoCatalog.repository.LocationRepository;
import com.dev.photoCatalog.repository.PhotoshootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    // Get a photoshoot by ID
    public Photoshoot getPhotoshootById(int id) {
        Optional<Photoshoot> photoshoot = photoshootRepository.findById(id);
        return photoshoot.orElse(null);
    }

    // Get all photos in a photoshoot by photoshoot ID
    public List<Photo> getPhotosForPhotoshoot(int id) {
        Photoshoot photoshoot = getPhotoshootById(id);
        List<Photo> photos = new ArrayList<>();
        if (photoshoot != null) {
            int[] photoIDs = photoshoot.getPhotoIDArray();
            for (int photoID : photoIDs) {
                Optional<Photo> photo = photoRepository.findById(photoID);
                photo.ifPresent(photos::add);
            }
        }
        return photos;
    }

    // Get all locations in a photoshoot by photoshoot ID
    public List<Location> getLocationsForPhotoshoot(int id) {
        Photoshoot photoshoot = getPhotoshootById(id);
        List<Location> locations = new ArrayList<>();
        if (photoshoot != null) {
            int[] locationIDs = photoshoot.getLocationIDArray();
            for (int locationID : locationIDs) {
                Optional<Location> location = locationRepository.findById(locationID);
                location.ifPresent(locations::add);
            }
        }
        return locations;
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
            photoshoot.setPhotoIDs(updatedPhotoshoot.getPhotoIDs());
            photoshoot.setLocationIDs(updatedPhotoshoot.getLocationIDs());
            photoshoot.setDate(updatedPhotoshoot.getDate());
            return photoshootRepository.save(photoshoot);
        }
        return null; 
    }

    // Delete a photoshoot by ID
    public void deletePhotoshoot(int id) {
        photoshootRepository.deleteById(id);
    }
}
