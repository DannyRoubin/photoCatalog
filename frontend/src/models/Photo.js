class Photo {
  constructor(photoID, photoGUID, fileName, timeStamp) {
    this.photoID = photoID; 
    this.photoGUID = photoGUID; 
    this.fileName = fileName; 
    this.timeStamp = timeStamp;
  }

  // Getters
  getPhotoID() {
    return this.photoID;
  }

  getPhotoGUID() {
    return this.photoGUID;
  }

  getFileName() {
    return this.fileName;
  }

  getTimeStamp() {
    return this.timeStamp;
  }

  // Setters
  setPhotoID(photoID) {
    this.photoID = photoID;
  }

  setPhotoGUID(photoGUID) {
    this.photoGUID = photoGUID;
  }

  setFileName(fileName) {
    this.fileName = fileName;
  }

  setTimeStamp(timeStamp) {
    this.timeStamp = timeStamp;
  }
}

export default Photo;
