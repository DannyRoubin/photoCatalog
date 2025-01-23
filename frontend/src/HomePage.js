import React, { useState, useEffect } from "react";
import { addPhotoshoot, getAllPhotoshoots } from "./photoshootService";
import { useNavigate } from "react-router-dom";
import "./App.css";

function HomePage() {
  const [photoshoots, setPhotoshoots] = useState([]);
  const [newDate, setNewDate] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  // Grabs all photoshoots whenever state changes
  useEffect(() => {fetchPhotoshoots();}, []);

  //function to grab all photoshoots and set them on rerenders
  async function fetchPhotoshoots() {
    try {
      const data = await getAllPhotoshoots();
      setPhotoshoots(data);
    } catch (error) {
      console.log("Error grabbing photoshoots:", error);
    }
  }

  // Handle form submission to add a new photoshoot
  const handleAddPhotoshoot = async (e) => {
    e.preventDefault();
    if (!newDate) {
      alert("Please select a date!");
      return;
    }
    setLoading(true);
    try {
      const newPhotoshoot = await addPhotoshoot(newDate);
      fetchPhotoshoots(); 
      setNewDate(""); 
    } catch (error) {
      console.log("Error adding photoshoot:", error);
    } finally {
      setLoading(false);
    }
  };

  // Navigate to the Photoshoot page
  const handlePhotoshootClick = (photoshootID) => {
    navigate(`/photoshoot/${photoshootID}`);
  };

  return (
    <div className="homepage-container">
      <h1>Photoshoots</h1>

      {/* Section to add new photoshoots */}
      <form onSubmit={handleAddPhotoshoot} className="new-photoshoot-form">
        <h2>Create New Photoshoot</h2>
        <label htmlFor="photoshoot-date">Date:</label>
        <input
          type="datetime-local"
          id="photoshoot-date"
          value={newDate}
          onChange={(e) => setNewDate(e.target.value)}
        />
        <button type="submit" disabled={loading}>
          {loading ? "Adding..." : "Submit"}
        </button>
      </form>

      {/* Section to display existing photoshoots */}
      <div className="existing-photoshoots">
        <h2>Existing Photoshoots</h2>
        {photoshoots.length === 0 ? (
          <p>No photoshoots available.</p>
        ) : (
          <ul>
            {photoshoots.map((photoshoot) => (
              <li key={photoshoot.photoshootID}>
                <button
                  className="photoshoot-button"
                  onClick={() => handlePhotoshootClick(photoshoot.photoshootID)}
                >
                  Photoshoot #{photoshoot.photoshootID}
                </button>
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
}

export default HomePage;
