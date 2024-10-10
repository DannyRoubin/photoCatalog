package com.dev.photoCatalog.controller;

import com.dev.photoCatalog.model.Photoshoot;
import com.dev.photoCatalog.model.Location;
import com.dev.photoCatalog.repository.PhotoshootRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class PhotoshootControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PhotoshootRepository photoshootRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        photoshootRepository.deleteAll();
    }

    @Test
    public void testGetAllPhotoshoots() throws Exception {
        mockMvc.perform(get("/photoshoot"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateNewPhotoshoot() throws Exception {
        Location location = new Location();
        location.setLocationID(1);
        location.setLocationName("Redmond");

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        Photoshoot newPhotoshoot = new Photoshoot();
        newPhotoshoot.setDate(timestamp);
        newPhotoshoot.setLocation(location);

        mockMvc.perform(post("/photoshoot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPhotoshoot)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.photoshootID").exists());
    }

    @Test
    public void testGetPhotoshootById() throws Exception {
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        Photoshoot photoshoot = new Photoshoot();
        photoshoot.setDate(timestamp);
        Location location = new Location();
        location.setLocationID(1);
        location.setLocationName("Redmond");
        photoshoot.setLocation(location);

        photoshoot = photoshootRepository.save(photoshoot);

        mockMvc.perform(get("/photoshoot/" + photoshoot.getPhotoshootID()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.photoshootID").value(photoshoot.getPhotoshootID()));
    }

    @Test
    public void testUpdatePhotoshoot() throws Exception {
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        Photoshoot photoshoot = new Photoshoot();
        Location location = new Location();
        location.setLocationID(1);
        location.setLocationName("Redmond");
        photoshoot.setDate(timestamp);
        photoshoot.setLocation(location);
        photoshoot = photoshootRepository.save(photoshoot);

        photoshoot.getLocation().setLocationID(2);

        mockMvc.perform(put("/photoshoot/" + photoshoot.getPhotoshootID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(photoshoot)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location.locationName").value("Seattle"));
    }

    @Test
    public void testDeletePhotoshoot() throws Exception {
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        Photoshoot photoshoot = new Photoshoot();
        Location location = new Location();
        location.setLocationID(1);
        location.setLocationName("Redmond");
        photoshoot.setDate(timestamp);
        photoshoot.setLocation(location);
        photoshoot = photoshootRepository.save(photoshoot);

        mockMvc.perform(delete("/photoshoot/" + photoshoot.getPhotoshootID()))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddPhotoToPhotoshoot() throws Exception {
        // Assuming you have a Photoshoot and a Photo ready in the repository.
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        Photoshoot photoshoot = new Photoshoot();
        Location location = new Location();
        location.setLocationID(1);
        location.setLocationName("Redmond");
        photoshoot.setDate(timestamp);
        photoshoot.setLocation(location);
        photoshoot = photoshootRepository.save(photoshoot);

        String photoGUID = "D4E3504D-0B6D-4888-96E5-30C2CCE4E399";

        mockMvc.perform(post("/photoshoot/" + photoshoot.getPhotoshootID() + "/addPhoto/" + photoGUID))
                .andExpect(status().isOk());
    }
}
