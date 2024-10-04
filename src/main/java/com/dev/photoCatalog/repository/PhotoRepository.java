package com.dev.photoCatalog.repository;

import com.dev.photoCatalog.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    // Custom query to find all photos by photoshoot ID via the junction table
    @Query(value = "SELECT p.* FROM Photo p JOIN PhotoshootPhotoJunction ppj ON p.photoID = ppj.photoID WHERE ppj.photoshootID = :photoshootId", nativeQuery = true)
    List<Photo> findAllPhotosForPhotoshoot(@Param("photoshootId") int photoshootId);


    // Custom query to find photo by photoGUID
    Photo findByPhotoGUID(java.util.UUID photoGUID);
}
