import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import HomePage from './HomePage';
import PhotoshootPage from "./PhotoshootPage";


function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/photoshoot/:photoshootID" element={<PhotoshootPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;