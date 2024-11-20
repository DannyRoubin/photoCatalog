import axios from 'axios';

export async function getPhotoById(id) {
  try {
    const res = await axios.get(`http://localhost:8080/photo/${id}`);
    if (res.status !== 200) {
      throw new Error(`Network response was not ok: ${res.status} - ${res.statusText}`);
    }
    return res.data;
  } catch (error) {
    console.error('Error fetching photo:', error);
    throw new Error(`Error fetching photo: ${error.message}`);
  }
}

export async function sendPostRequest() {
  const photoID = 8;
  const photoGUID = "0ED788CF-3309-604D-ABA7-0394FCFFF553";

  try {
    // Fetch the image file from the public directory
    const response = await fetch('/sample.jpg');
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    const imageBlob = await response.blob();

    // Debugging: Log the first 10 bytes of the image as a Uint8Array
    const imageBuffer = await imageBlob.arrayBuffer();
    console.log('Image Byte Array (First 10 Bytes):', new Uint8Array(imageBuffer).slice(0, 10));

    // Prepare the FormData object
    const formData = new FormData();
    formData.append("photoID", photoID);
    formData.append("photoGUID", photoGUID);
    formData.append("file", new File([imageBlob], "sample.jpg", { type: "image/jpeg" }));

    // Send the POST request
    const result = await axios.post("http://localhost:8081/api/process-image", formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });

    console.log('Post request successful:', result.data);
  } catch (error) {
    console.error('Error in post request:', error);
  }
}


