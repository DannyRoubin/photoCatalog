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

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public PhotoshootDTO convertToDTO(Photoshoot photoshoot) {
        return new PhotoshootDTO(
                photoshoot.getPhotoshootID(),
                photoshoot.getDate(),
                photoshoot.getLocationID()  // Use the locationID directly
        );
    }

    public Photoshoot convertToEntity(PhotoshootDTO dto) {
        Photoshoot photoshoot = new Photoshoot();
        photoshoot.setPhotoshootID(dto.getPhotoshootID());
        photoshoot.setDate(dto.getDate());
        photoshoot.setLocationID(dto.getLocationID());  // Use locationID directly
        return photoshoot;
    }

    // Get all photoshoots and return as DTOs
    public List<PhotoshootDTO> getAllPhotoshoots() {
        return photoshootRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get a specific photoshoot by ID
    public Optional<PhotoshootDTO> getPhotoshootById(int id) {
        return photoshootRepository.findById(id).map(this::convertToDTO);
    }

    // Add a new photoshoot
    public PhotoshootDTO addPhotoshoot(PhotoshootDTO dto) {
        Photoshoot savedPhotoshoot = photoshootRepository.save(convertToEntity(dto));
        return convertToDTO(savedPhotoshoot);
    }

    // Update an existing photoshoot
    public Optional<PhotoshootDTO> updatePhotoshoot(int id, PhotoshootDTO dto) {
        return photoshootRepository.findById(id).map(existing -> {
            existing.setDate(dto.getDate());

            // Use locationID directly instead of fetching the Location object
            existing.setLocationID(dto.getLocationID());

            Photoshoot updated = photoshootRepository.save(existing);
            return convertToDTO(updated);
        });
    }

    // Delete a photoshoot by ID
    public void deletePhotoshoot(int id) {
        photoshootRepository.deleteById(id);
    }

    // Get all photos associated with a specific photoshoot by querying the junction table
    public List<Photo> getAllPhotosForPhotoshoot(int photoshootId) {
        return photoRepository.findAllPhotosForPhotoshoot(photoshootId);
    }

    // Get all locations for a specific photoshoot by querying the junction table
    public List<Location> getAllLocationsForPhotoshoot(int photoshootId) {
        return locationRepository.findAllLocationsForPhotoshoot(photoshootId);
    }

    // Add a photo to a photoshoot using photoGUID
    @Transactional
    public void addPhotoToPhotoshoot(int photoshootID, UUID photoGUID) {
        Photoshoot photoshoot = photoshootRepository.findById(photoshootID)
                .orElseThrow(() -> new IllegalArgumentException("Photoshoot not found"));

        Photo photo = photoRepository.findByPhotoGUID(photoGUID.toString())
                .orElseThrow(() -> new IllegalArgumentException("Photo not found"));

        String sql = "INSERT INTO PhotoshootPhotoJunction (photoshootID, photoGUID) VALUES (?, ?)";
        jdbcTemplate.update(sql, photoshoot.getPhotoshootID(), photo.getPhotoGUID());
    }

    // Check if a photoshoot exists by ID
    public boolean photoshootExists(int id) {
        return photoshootRepository.existsById(id);
    }
}
