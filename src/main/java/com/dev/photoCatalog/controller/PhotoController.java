package com.dev.photoCatalog.controller;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    private List<String> photos = new ArrayList<>();

    // GET endpoint to retrieve all photos
    @GetMapping
    public List<String> getAllPhotos() {
        return photos;
    }

    // POST endpoint to add a new photo
    @PostMapping
    public String addPhoto(@RequestParam String photoName) {
        photos.add(photoName);
        return "Photo added successfully: " + photoName;
    }

    //Temp test endpoint to ensure that I have endpoints working properly
    @GetMapping("/test")
        public String testEndpoint() {
        return "Test Endpoint Working";
    }

}
