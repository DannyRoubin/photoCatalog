class Location {
    constructor(locationID, name, address) {
      this.locationID = locationID; 
      this.name = name; 
      this.address = address;
    }
  
    // Getters
    getLocationID() {
      return this.locationID;
    }
  
    getName() {
      return this.name;
    }
  
    getAddress() {
      return this.address;
    }
  
    // Setters
    setLocationID(locationID) {
      this.locationID = locationID;
    }
  
    setName(name) {
      this.name = name;
    }
  
    setAddress(address) {
      this.address = address;
    }
  }
  
  export default Location;
  