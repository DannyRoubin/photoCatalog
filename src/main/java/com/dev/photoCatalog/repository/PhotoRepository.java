package com.dev.photoCatalog.repository;

import com.dev.photoCatalog.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    // Custom query to find all photos by photoshoot ID via the junction table
    @Query(value = "SELECT p.* FROM Photo p JOIN PhotoshootPhotoJunction ppj ON p.photoGUID = ppj.photoGUID WHERE ppj.photoshootID = ?", nativeQuery = true)
    List<Photo> findAllPhotosForPhotoshoot(@Param("photoshootId") int photoshootId);
//select p.* from Photoshoot ps
//inner join PhotoshootPhotoJunction ppj on ps.PhotoshootID = ppj.PhotoshootID
//inner join Photo p on ppj.PhotoGUID = p.PhotoGUID
//where ps.PhotoshootID = 1

    // Custom query to find photo by photoGUID
    Optional<Photo> findByPhotoGUID(String photoGUID);
}
