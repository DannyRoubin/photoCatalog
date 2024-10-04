package com.dev.photoCatalog.repository;

import com.dev.photoCatalog.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    // Use native SQL query to find locations for a specific photoshootID
    @Query(value = "SELECT l.* FROM Location l JOIN PhotoshootLocationJunction plj ON l.locationID = plj.locationID WHERE plj.photoshootID = :photoshootId", nativeQuery = true)
    List<Location> findAllLocationsForPhotoshoot(@Param("photoshootId") int photoshootId);
}
