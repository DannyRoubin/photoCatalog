import React from 'react';
import '../styles/App.css';

const PhotoList = ({ photos }) => {
  return (
    // {/* Each photo in photoshoot */}
    // {/* Note for self, load each image here as well */}
    // {/* Turn this into a grid  https://mui.com/material-ui/react-grid2/ */}
    <div className="photos">
      <h2>Existing Images</h2>
      {photos.length === 0 ? (
        <p>No images found for this photoshoot.</p>
      ) : (
        photos.map((photo) => (
          <div key={photo.getPhotoGUID()} className="photo-item">
            <p>File Name: {photo.getFileName()}</p>
          </div>
        ))
      )}
    </div>
  );
};

export default PhotoList;