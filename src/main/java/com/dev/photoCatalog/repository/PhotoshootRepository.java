package com.dev.photoCatalog.repository;

import com.dev.photoCatalog.model.Photoshoot;  
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoshootRepository extends JpaRepository<Photoshoot, Integer> {

}
