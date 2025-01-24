import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/App.css';

const PhotoshootList = ({ photoshoots }) => {
  const navigate = useNavigate();

  const handlePhotoshootClick = (photoshootID) => {
    navigate(`/photoshoot/${photoshootID}`);
  };

  return (
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
  );
};

export default PhotoshootList;