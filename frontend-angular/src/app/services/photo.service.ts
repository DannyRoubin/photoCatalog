import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, throwError, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PhotoService {
  private backendUrl = 'http://localhost:8080/photo';
  private functionAppUrl = 'http://localhost:8081/api/process-image';

  constructor(private http: HttpClient) {}

  // Fetch a photo by ID
  getPhotoById(id: number): Observable<any> {
    return this.http.get(`${this.backendUrl}/${id}`).pipe(
      catchError((error: HttpErrorResponse) => this.handleError(error))
    );
  }

  // Send the metadata to the backend
  sendPostRequest(fileName: string, imageDate: string): Observable<any> {
    const payload = { fileName, timeStamp: imageDate };
    return this.http.post(this.backendUrl, payload).pipe(
      catchError((error: HttpErrorResponse) => this.handleError(error))
    );
  }

  // Send the image file to the function app
  sendImageToFunctionApp(photoID: number, photoGUID: string, file: File): Observable<any> {
    const formData = new FormData();
    formData.append('photoID', photoID.toString());
    formData.append('photoGUID', photoGUID);
    formData.append('file', file);

    return this.http.post(this.functionAppUrl, formData).pipe(
      catchError((error: HttpErrorResponse) => this.handleError(error))
    );
  }

  // Error handler
  private handleError(error: HttpErrorResponse) {
    console.error('Error:', error);
    return throwError(() => new Error(`Error: ${error.message}`));
  }
}
