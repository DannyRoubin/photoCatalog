class Photoshoot {
    constructor(photoshootID, date) {
      this.photoshootID = photoshootID; 
      this.date = date; 
    }
  
    // Getters
    getPhotoshootID() {
      return this.photoshootID;
    }
  
    getDate() {
      return this.date;
    }
  
    // Setters
    setPhotoshootID(photoshootID) {
      this.photoshootID = photoshootID;
    }
  
    setDate(date) {
      this.date = date;
    }
  }
  
  export default Photoshoot;
  