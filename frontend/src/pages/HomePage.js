import React, { useState, useEffect } from "react";
import { getAllPhotoshoots, addPhotoshoot } from "../services/photoshootService";
import PhotoshootList from "../components/PhotoshootList";
import NewPhotoshootForm from "../components/NewPhotoshootForm";
import '../styles/App.css';

function HomePage() {
  const [photoshoots, setPhotoshoots] = useState([]);

  useEffect(() => {
    fetchPhotoshoots();
  }, []);

  async function fetchPhotoshoots() {
    try {
      const data = await getAllPhotoshoots();
      setPhotoshoots(data);
    } catch (error) {
      console.log("Error grabbing photoshoots:", error);
    }
  }

  const handleAddPhotoshoot = async (date) => {
    try {
      await addPhotoshoot(date);
      fetchPhotoshoots();
    } catch (error) {
      console.log("Error adding photoshoot:", error);
    }
  };

  return (
    <div className="homepage-container">
      <h1>Photoshoots</h1>
      <NewPhotoshootForm onAddPhotoshoot={handleAddPhotoshoot} />
      <PhotoshootList photoshoots={photoshoots} />
    </div>
  );
}

export default HomePage;
