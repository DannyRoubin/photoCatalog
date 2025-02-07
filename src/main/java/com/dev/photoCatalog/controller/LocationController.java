package com.dev.photoCatalog.controller;

import com.dev.photoCatalog.model.Location;
import com.dev.photoCatalog.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://witty-glacier-0b958821e.4.azurestaticapps.net"})
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    // Get all locations
    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    // Get a specific location by ID
    @GetMapping("/{id}")
    public Location getLocationById(@PathVariable int id) {
        return locationService.getLocationById(id);
    }

    // Add a new location
    @PostMapping
    public Location addLocation(@RequestBody Location location) {
        return locationService.addLocation(location);
    }

    // Update an existing location
    @PutMapping("/{id}")
    public Location updateLocation(@PathVariable int id, @RequestBody Location location) {
        return locationService.updateLocation(id, location);
    }

    // Delete a location by ID
    @DeleteMapping("/{id}")
    public String deleteLocation(@PathVariable int id) {
        locationService.deleteLocation(id);
        return "Location deleted successfully.";
    }
}
