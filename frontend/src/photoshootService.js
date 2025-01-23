import axios from 'axios';

//get all photoshoots.
export async function getAllPhotoshoots() {
  try {
    const response = await axios.get("http://localhost:8080/photoshoot");
    if (response.status !== 200) {
      console.log(`${response.status} - ${response.statusText}`);
    }
    //console.log("hit check");
    return response.data;
  } catch (error) {
    console.log("Error fetching photoshoots:", error);
  }
}

//get a specific photoshoot by ID.
export async function getPhotoshootById(id) {
  try {
    const response = await axios.get(`http://localhost:8080/photoshoot/${id}`);
    if (response.status !== 200) {
      console.log(`${response.status} - ${response.statusText}`);
    }
    return response.data;
  } catch (error) {
    console.log("Error fetching photoshoot:", error);
  }
}

//make a new photoshoot.
export async function addPhotoshoot(date) {
    try {
      const response = await axios.post("http://localhost:8080/photoshoot", {
        //keeping a placeholder locationID for now. Will come back and allow users to select location later on
        locationID: 1, 
        date: date,
      });
      if (response.status !== 200) {
        console.log(`${response.status} - ${response.statusText}`);
      }
      return response.data;
    } catch (error) {
      console.log("Error creating photoshoot:", error);
    }
  }
  

 //add a photo to a photoshoot using the photoGUID.
export async function addPhotoToPhotoshoot(photoshootID, photoGUID) {
  try {
    const response = await axios.post(`http://localhost:8080/photoshoot/${photoshootID}/addPhoto/${photoGUID}`);
    if (response.status !== 200) {
      console.log(`${response.status} - ${response.statusText}`);
    }
  } catch (error) {
    console.log("Error adding photo to photoshoot:", error);
  }
}

 //get all photos for a specific photoshoot.
export async function getAllPhotosForPhotoshoot(photoshootID) {
  try {
    const response = await axios.get(`http://localhost:8080/photoshoot/${photoshootID}/photo`);
    if (response.status !== 200) {
      console.log(`${response.status} - ${response.statusText}`);
    }
    return response.data;
  } catch (error) {
    console.log("Error grabbing photos for photoshoot:", error);
  }
}
