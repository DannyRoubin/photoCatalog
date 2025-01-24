import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import {
  getAllPhotosForPhotoshoot,
  getPhotoshootById,
  addPhotoToPhotoshoot,
} from "../services/photoshootService";
import { sendPostRequest } from "../services/photoService";
import PhotoList from "../components/PhotoList";
import ImageUploadForm from "../components/ImageUploadForm";
import '../styles/App.css';

function PhotoshootPage() {
  const { photoshootID } = useParams();
  const navigate = useNavigate();

  const [photos, setPhotos] = useState([]);
  const [photoshootDate, setPhotoshootDate] = useState("");

  useEffect(() => {
    fetchPhotoshootDetails();
  }, [photoshootID]);

  async function fetchPhotoshootDetails() {
    try {
      const photoshoot = await getPhotoshootById(photoshootID);
      setPhotoshootDate(photoshoot.getDate());

      const photos = await getAllPhotosForPhotoshoot(photoshootID);
      setPhotos(photos);
    } catch (error) {
      console.error("Error fetching photoshoot details or photos:", error);
    }
  }

  const handleUpload = async (selectedImage) => {
    const imageDate = new Date().toISOString();
    try {
      const photoData = await sendPostRequest(
        selectedImage.name,
        selectedImage,
        imageDate
      );

      await addPhotoToPhotoshoot(photoshootID, photoData.getPhotoGUID());

      const updatedPhotos = await getAllPhotosForPhotoshoot(photoshootID);
      setPhotos(updatedPhotos);
    } catch (error) {
      console.error("Error uploading image:", error);
    }
  };

  return (
    <div className="photoshoot-page-container">
      <h1>Photoshoot #{photoshootID}</h1>
      <p>Date: {photoshootDate}</p>
      <button onClick={() => navigate("/")}>Back to Home</button>
      <ImageUploadForm onUpload={handleUpload} />
      <PhotoList photos={photos} />
    </div>
  );
}

export default PhotoshootPage;