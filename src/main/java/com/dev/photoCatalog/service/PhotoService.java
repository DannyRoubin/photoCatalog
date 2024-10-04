package com.dev.photoCatalog.service;

import com.dev.photoCatalog.model.Photo;
import com.dev.photoCatalog.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    // Get all photos
    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    // Get a photo by ID
    public Photo getPhotoById(int id) {
        Optional<Photo> photo = photoRepository.findById(id);
        return photo.orElse(null);
    }

    // Add a new photo
    public Photo addPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    // Update an existing photo
    public Photo updatePhoto(int id, Photo updatedPhoto) {
        Optional<Photo> existingPhoto = photoRepository.findById(id);
        if (existingPhoto.isPresent()) {
            Photo photo = existingPhoto.get();
            photo.setFileName(updatedPhoto.getFileName());
            photo.setTimeStamp(updatedPhoto.getTimeStamp());
            return photoRepository.save(photo);
        }
        return null;  
    }

    // Delete a photo by ID
    public void deletePhoto(int id) {
        photoRepository.deleteById(id);
    }

    public Photo getPhotoByGUID(UUID photoGUID) {
        return photoRepository.findByPhotoGUID(photoGUID);
    }
}
