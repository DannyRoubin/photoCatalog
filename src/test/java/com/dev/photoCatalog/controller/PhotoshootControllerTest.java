package com.dev.photoCatalog.controller;

import com.dev.photoCatalog.dto.PhotoshootDTO;
import com.dev.photoCatalog.model.Photoshoot;
import com.dev.photoCatalog.repository.PhotoshootRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.MvcResult;

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
        PhotoshootDTO newPhotoshootDTO = new PhotoshootDTO(null,
                Timestamp.valueOf(LocalDateTime.now()), 1);

        mockMvc.perform(post("/photoshoot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPhotoshootDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.photoshootID").exists());
    }

    @Test
    public void testGetPhotoshootById() throws Exception {
        Photoshoot photoshoot = new Photoshoot();
        photoshoot.setDate(Timestamp.valueOf(LocalDateTime.now()));
        photoshoot.setLocationID(1);  // Setting locationID directly
        photoshoot = photoshootRepository.save(photoshoot);

        mockMvc.perform(get("/photoshoot/" + photoshoot.getPhotoshootID()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.photoshootID").value(photoshoot.getPhotoshootID()));
    }

    @Test
    public void testUpdatePhotoshoot() throws Exception {
        Photoshoot photoshoot = new Photoshoot();
        photoshoot.setDate(Timestamp.valueOf(LocalDateTime.now()));
        photoshoot.setLocationID(1);  // Initial locationID
        photoshoot = photoshootRepository.save(photoshoot);

        // Creating an updated PhotoshootDTO with new locationID
        PhotoshootDTO updatedDTO = new PhotoshootDTO(
                photoshoot.getPhotoshootID(),
                Timestamp.valueOf(LocalDateTime.now()),
                2  // New locationID
        );

        mockMvc.perform(put("/photoshoot/" + photoshoot.getPhotoshootID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.locationID").value(2));
    }

    @Test
    public void testDeletePhotoshoot() throws Exception {
        Photoshoot photoshoot = new Photoshoot();
        photoshoot.setDate(Timestamp.valueOf(LocalDateTime.now()));
        photoshoot.setLocationID(1);  // Setting locationID
        photoshoot = photoshootRepository.save(photoshoot);

        mockMvc.perform(delete("/photoshoot/" + photoshoot.getPhotoshootID()))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddPhotoToPhotoshootAsync() throws Exception {
        // Create a new photoshoot
        Photoshoot photoshoot = new Photoshoot();
        photoshoot.setDate(Timestamp.valueOf(LocalDateTime.now()));
        photoshoot.setLocationID(1); // Set locationID directly
        photoshoot = photoshootRepository.save(photoshoot);

        // Example Photo GUID to add
        String photoGUID = "D4E3504D-0B6D-4888-96E5-30C2CCE4E399";

        // Perform the async POST request
        MvcResult mvcResult = mockMvc.perform(post("/photoshoot/" + photoshoot.getPhotoshootID() + "/addPhoto/" + photoGUID))
                .andExpect(request().asyncStarted())  // Ensure async request is started
                .andReturn();

        // Use asyncDispatch to wait for the async processing to complete and validate response
        mockMvc.perform(asyncDispatch(mvcResult))
                .andDo(print())  // Print response for debugging if needed
                .andExpect(status().isOk())
                .andExpect(content().string("Photo added to Photoshoot successfully!"));
    }
}
