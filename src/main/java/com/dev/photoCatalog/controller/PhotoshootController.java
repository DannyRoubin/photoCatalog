package com.dev.photoCatalog.controller;

import com.dev.photoCatalog.model.Photoshoot;
import com.dev.photoCatalog.service.PhotoshootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photoshoots")
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
}
