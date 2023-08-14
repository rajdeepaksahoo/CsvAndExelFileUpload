import { Component, OnInit } from '@angular/core';
import { UploadService } from '../upload.service';
import { HttpEventType } from '@angular/common/http';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {
  selectedFile!: File;
  selectedFileType: string = 'csv'; // Default selection
  progress: number = 0;
  message: string = '';

  constructor(private uploadService: UploadService) {}

  ngOnInit(): void {}

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  onUpload(): void {
    if (this.selectedFile) {
      if (this.selectedFileType === 'csv') {
        this.uploadCsv(this.selectedFile);
      } else if (this.selectedFileType === 'excel') {
        this.uploadExcel(this.selectedFile);
      }
    }
  }

  private uploadCsv(file: File) {
    this.uploadService.uploadCsvFile(file).subscribe(
      (event: any) => {
        this.handleUploadEvent(event);
      },
      (error) => {
        this.handleError(error);
      }
    );
  }

  private uploadExcel(file: File) {
    this.uploadService.uploadExcelFile(file).subscribe(
      (event: any) => {
        this.handleUploadEvent(event);
      },
      (error) => {
        this.handleError(error);
      }
    );
  }

  private handleUploadEvent(event: any) {
    if (event.type === HttpEventType.UploadProgress) {
      this.progress = Math.round((100 * event.loaded) / event.total);
    } else if (event.type === HttpEventType.Response) {
      this.message = 'File uploaded successfully!';
      this.progress = 0;
    }
  }

  private handleError(error: any) {
    console.error(error);
    this.message = 'File upload failed.';
  }
}
