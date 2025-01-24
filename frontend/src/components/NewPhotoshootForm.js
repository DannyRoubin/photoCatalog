import React, { useState } from 'react';
import '../styles/App.css';

const NewPhotoshootForm = ({ onAddPhotoshoot }) => {
  const [newDate, setNewDate] = useState("");
  const [loading, setLoading] = useState(false);

  const handleAddPhotoshoot = async (e) => {
    e.preventDefault();
    if (!newDate) {
      alert("Please select a date!");
      return;
    }
    setLoading(true);
    try {
      await onAddPhotoshoot(newDate);
      setNewDate("");
    } catch (error) {
      console.log("Error adding photoshoot:", error);
    } finally {
      setLoading(false);
    }
  };

  return (
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
  );
};

export default NewPhotoshootForm;