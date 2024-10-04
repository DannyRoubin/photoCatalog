package com.dev.photoCatalog.service;

import com.dev.photoCatalog.model.Location;
import com.dev.photoCatalog.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    // Get all locations
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    // Get a location by ID
    public Location getLocationById(int id) {
        Optional<Location> location = locationRepository.findById(id);
        return location.orElse(null);
    }

    // Add a new location
    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    // Update an existing location
    public Location updateLocation(int id, Location updatedLocation) {
        Optional<Location> existingLocation = locationRepository.findById(id);
        if (existingLocation.isPresent()) {
            Location location = existingLocation.get();
            location.setLocationName(updatedLocation.getLocationName());
            return locationRepository.save(location);
        }
        return null;  
    }

    // Delete a location by ID
    public void deleteLocation(int id) {
        locationRepository.deleteById(id);
    }
}
