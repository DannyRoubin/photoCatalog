import React, { useState, useEffect } from "react";
import { getAllPhotoshoots, addPhotoshoot } from "../services/photoshootService";
import { getAllLocations, addLocation } from "../services/LocationService";
import PhotoshootList from "../components/PhotoshootList";
import NewPhotoshootForm from "../components/NewPhotoshootForm";
import "../styles/App.css";

function HomePage() {
  const [photoshoots, setPhotoshoots] = useState([]);
  const [locations, setLocations] = useState([]);

  useEffect(() => {
    fetchPhotoshoots();
    fetchLocations();
  }, []);

  async function fetchPhotoshoots() {
    try {
      const data = await getAllPhotoshoots();
      setPhotoshoots(data); 
    } catch (error) {
      console.error("Error grabbing photoshoots:", error);
    }
  }

  async function fetchLocations() {
    try {
      const data = await getAllLocations();
      setLocations(data); 
    } catch (error) {
      console.error("Error grabbing locations:", error);
    }
  }

  const handleAddPhotoshoot = async (date, locationID) => {
    try {
      await addPhotoshoot(date, locationID);
      fetchPhotoshoots(); 
    } catch (error) {
      console.error("Error adding photoshoot:", error);
    }
  };

  const handleAddLocation = async (name) => {
    try {
      await addLocation(name);
      fetchLocations(); 
    } catch (error) {
      console.error("Error adding location:", error);
    }
  };

  return (
    <div className="homepage-container">
      <h1>Photoshoots</h1>
      <NewPhotoshootForm
        onAddPhotoshoot={handleAddPhotoshoot}
        locations={locations} 
        onAddLocation={handleAddLocation} 
      />
      <PhotoshootList photoshoots={photoshoots} locations={locations}/>
    </div>
  );
}

export default HomePage;
