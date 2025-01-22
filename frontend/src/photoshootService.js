import axios from 'axios';

/**
 * Fetch all photoshoots.
 */
export async function getAllPhotoshoots() {
  try {
    const response = await axios.get("http://localhost:8080/photoshoot");
    if (response.status !== 200) {
      throw new Error(`Network response was not ok: ${response.status} - ${response.statusText}`);
    }
    return response.data;
  } catch (error) {
    console.error("Error fetching photoshoots:", error);
    throw new Error(`Error fetching photoshoots: ${error.message}`);
  }
}

/**
 * Fetch a specific photoshoot by ID.
 */
export async function getPhotoshootById(id) {
  try {
    const response = await axios.get(`http://localhost:8080/photoshoot/${id}`);
    if (response.status !== 200) {
      throw new Error(`Network response was not ok: ${response.status} - ${response.statusText}`);
    }
    return response.data;
  } catch (error) {
    console.error("Error fetching photoshoot:", error);
    throw new Error(`Error fetching photoshoot: ${error.message}`);
  }
}

/**
 * Create a new photoshoot.
 */
export async function addPhotoshoot(date) {
    try {
      const response = await axios.post("http://localhost:8080/photoshoot", {
        //keeping a placeholder locationID for now. Will come back and allow users to select location later on
        locationID: 1, 
        date: date,
      });
      if (response.status !== 200) {
        throw new Error(`Network response was not ok: ${response.status} - ${response.statusText}`);
      }
      return response.data;
    } catch (error) {
      console.error("Error creating photoshoot:", error);
      throw new Error(`Error creating photoshoot: ${error.message}`);
    }
  }
  

/**
 * Add a photo to a photoshoot by photoGUID.
 */
export async function addPhotoToPhotoshoot(photoshootID, photoGUID) {
  try {
    const response = await axios.post(`http://localhost:8080/photoshoot/${photoshootID}/addPhoto/${photoGUID}`);
    if (response.status !== 200) {
      throw new Error(`Network response was not ok: ${response.status} - ${response.statusText}`);
    }
    console.log("Photo added to photoshoot:", response.data);
  } catch (error) {
    console.error("Error adding photo to photoshoot:", error);
    throw new Error(`Error adding photo to photoshoot: ${error.message}`);
  }
}

/**
 * Fetch all photos for a specific photoshoot.
 */
export async function getAllPhotosForPhotoshoot(photoshootID) {
  try {
    const response = await axios.get(`http://localhost:8080/photoshoot/${photoshootID}/photo`);
    if (response.status !== 200) {
      throw new Error(`Network response was not ok: ${response.status} - ${response.statusText}`);
    }
    return response.data;
  } catch (error) {
    console.error("Error fetching photos for photoshoot:", error);
    throw new Error(`Error fetching photos for photoshoot: ${error.message}`);
  }
}
