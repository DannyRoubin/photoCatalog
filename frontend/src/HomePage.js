import React, { useState } from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import './App.css';
import { sendPostRequest } from './photoService';
import ExifReader from 'exifreader';

function NewPage() {
  const [selectedFile, setSelectedFile] = useState(null);
  const [fileName, setFileName] = useState('');
  const [imageDate, setImageDate] = useState('');

  const handleFileChange = (event) => {
    const file = event.target.files[0];
    setSelectedFile(file);
    setFileName(file.name);

    const reader = new FileReader();
    reader.onload = (e) => {
      try {
        const tags = ExifReader.load(e.target.result);
        if (tags.DateTimeOriginal) {
          setImageDate(tags.DateTimeOriginal.description);
        } else {
          setImageDate(new Date().toISOString());
        }
      } catch (error) {
        console.error('Error reading image metadata:', error);
        setImageDate(new Date().toISOString());
      }
    };
    reader.readAsArrayBuffer(file);
  };

  const handleSubmit = async () => {
    if (!selectedFile) {
      console.error('No file selected');
      return;
    }

    try {
      await sendPostRequest(fileName, selectedFile, imageDate);
    } catch (error) {
      console.error('Error sending request:', error);
    }
  };

  return (
    <Container className="page-container">
      <h1 className="page-title">Upload and Process Image</h1>
      <Row className="form-row">
        <Col xs={12} md={6} className="form-column">
          <input
            type="file"
            accept="image/*"
            className="file-input"
            onChange={handleFileChange}
          />
          {fileName && (
            <div className="file-info">
              <strong>Selected File:</strong> {fileName}
            </div>
          )}
          {imageDate && (
            <div className="file-info">
              <strong>Image Date:</strong> {imageDate}
            </div>
          )}
        </Col>
      </Row>
      <Row className="button-row">
        <Col xs="auto">
          <Button
            className="submit-button"
            onClick={handleSubmit}
            disabled={!selectedFile}
          >
            Submit
          </Button>
        </Col>
      </Row>
    </Container>
  );
}

export default NewPage;
