import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import {
  getAllPhotosForPhotoshoot,
  getPhotoshootById,
  addPhotoToPhotoshoot,
} from "./photoshootService";
import { sendPostRequest } from "./photoService";
import "./App.css";

function PhotoshootPage() {
  const { photoshootID } = useParams();
  const navigate = useNavigate();

  const [photos, setPhotos] = useState([]);
  const [selectedImage, setSelectedImage] = useState(null);
  const [imageDate, setImageDate] = useState("");
  const [loading, setLoading] = useState(false);
  const [photoshootDate, setPhotoshootDate] = useState("");

  // Grab the photoshoot details and existing photos in the photoshoot
  useEffect(() => {fetchPhotoshootDetails();}, [photoshootID]);

  async function fetchPhotoshootDetails() {
    try {
      // get date from the photoshoot itself
      const photoshoot = await getPhotoshootById(photoshootID);
      setPhotoshootDate(photoshoot.date);

      // grab all the photos from the photoshoot
      const photos = await getAllPhotosForPhotoshoot(photoshootID);
      setPhotos(photos);
    } catch (error) {
      console.error("Error fetching photoshoot details or photos:", error);
    }
  }

  // Handle file selection
  const handleFileChange = (e) => {
    const file = e.target.files[0];
    setSelectedImage(file);
    //using current date as a placeholder for now
    setImageDate(new Date().toISOString()); 
  };

  // Handle form submission
  const handleUpload = async (e) => {
    e.preventDefault();
    if (!selectedImage) {
      alert("Please select an image to upload.");
      return;
    }

    setLoading(true);
    try {
      // Process image through the function app and get the photoGUID
      const { photoGUID } = await sendPostRequest(
        selectedImage.name,
        selectedImage,
        imageDate
      );

      // Add photo to the photoshoot using the correct photoGUID
      await addPhotoToPhotoshoot(photoshootID, photoGUID);

      // Refresh the photo list
      const updatedPhotos = await getAllPhotosForPhotoshoot(photoshootID);
      setPhotos(updatedPhotos);

      // Clear the form
      setSelectedImage(null);
      setImageDate("");
    } catch (error) {
      console.error("Error uploading image:", error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="photoshoot-page-container">
      <h1>Photoshoot #{photoshootID}</h1>
      <p>Date: {photoshootDate}</p> 
      <button onClick={() => navigate("/")}>Back to Home</button>

      {/* Image Upload Section */}
      <form onSubmit={handleUpload} className="upload-form">
        <h2>Upload and Process Image</h2>
        <input type="file" onChange={handleFileChange} />
        <button type="submit" disabled={loading}>
          {loading ? "Uploading..." : "Submit"}
        </button>
      </form>

      {/* Each photo in photoshoot */}
      {/* Note for self, load each image here as well */}
      {/* Turn this into a grid  https://mui.com/material-ui/react-grid2/ */}
      <div className="photos">
        <h2>Existing Images</h2>
        {photos.length === 0 ? (
          <p>No images found for this photoshoot.</p>
        ) : (
          photos.map((photo) => (
            <div key={photo.photoGUID} className="photo-item">
              <p>File Name: {photo.fileName}</p>
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default PhotoshootPage;
