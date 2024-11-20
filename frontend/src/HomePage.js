import React, { useState } from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import './App.css'; 
import { getPhotoById, sendPostRequest } from './photoService';

function NewPage() {
  const [fileName, setFileName] = useState('');

  const handleButtonClick = async () => {
    try {
      const photo = await getPhotoById(8);
      setFileName(photo.fileName);
    } catch (error) {
      console.error('Error fetching photo:', error);
    }
  };

  const handlePostButtonClick = async () => {
    try {
      await sendPostRequest();
    } catch (error) {
      console.error('Error sending post request:', error);
    }
  };

  return (
    <div>
      <h1>Welcome to the New Page</h1>
      <p>This is a new page in your React app.</p>

      <Container fluid className="container-custom">
        <Row className="row-outline">
          <Col xs={3} className="col-outline">
            <Button onClick={handleButtonClick}>Get Photo</Button>
          </Col>
          <Col xs={9} className="col-outline">
            {fileName && <p>File Name: {fileName}</p>}
          </Col>
        </Row>

        <Row className="row-outline">
          <Col xs={3} className="col-outline">
            <Button onClick={handlePostButtonClick}>Send Post Request</Button>
          </Col>
          <Col xs={9} className="col-outline">
            {/* You can add any additional content here if needed */}
          </Col>
        </Row>

        <Row className="row-outline">
          <Col xs={3} className="col-outline">Left Column</Col>
          <Col xs={9} className="col-outline">Right Column</Col>
        </Row>

        <Row className="row-outline">
          <Col xs={3} className="col-outline">Left Column</Col>
          <Col xs={9} className="col-outline">Right Column</Col>
        </Row>

        <Row className="row-outline">
          <Col xs={3} className="col-outline">Left Column</Col>
          <Col xs={9} className="col-outline">Right Column</Col>
        </Row>
      </Container>
    </div>
  );
}

export default NewPage;