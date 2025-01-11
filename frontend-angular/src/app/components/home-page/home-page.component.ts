import { Component } from '@angular/core';
import { PhotoService } from '../../services/photo.service';
import { CommonModule } from '@angular/common';
import * as EXIF from 'exif-js';

@Component({
  selector: 'app-home-page',
  standalone: true,
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
  imports: [CommonModule],
})
export class HomePageComponent {
  selectedFile: File | null = null;
  fileName: string = '';
  imageDate: string = '';

  constructor(private photoService: PhotoService) {}

  // Handle file input change
  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    this.selectedFile = file;
    this.fileName = file.name;

    const reader = new FileReader();
    reader.onload = (e) => {
      try {
        const tags = EXIF.readFromBinaryFile(e.target?.result as ArrayBuffer);
        if (tags.DateTimeOriginal) {
          this.imageDate = tags.DateTimeOriginal;
        } else {
          this.imageDate = new Date(file.lastModified).toISOString();
        }
      } catch (error) {
        console.error('Error reading image metadata:', error);
        this.imageDate = new Date(file.lastModified).toISOString();
      }
    };
    reader.readAsArrayBuffer(file);
  }

  // Handle form submission
  onSubmit() {
    if (!this.selectedFile) {
      console.error('No file selected');
      return;
    }

    console.log('Sending metadata...');
    this.photoService
      .sendPostRequest(this.fileName, this.imageDate)
      .subscribe((backendResponse: any) => {
        console.log('Backend response:', backendResponse);

        const { photoID, photoGUID } = backendResponse;
        console.log('Sending file to function app...');

        this.photoService
          .sendImageToFunctionApp(photoID, photoGUID, this.selectedFile!)
          .subscribe((functionAppResponse: any) => {
            console.log('Function app response:', functionAppResponse);
          });
      });
  }
}
