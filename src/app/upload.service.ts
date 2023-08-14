import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Entity } from './Entity';

@Injectable({
  providedIn: 'root'
})
export class UploadService {
  private baseUrl = 'http://localhost:8080'; // Replace with your Spring Boot server URL

  constructor(private http: HttpClient) {}

  uploadCsvFile(file: File): Observable<HttpEvent<any>> {
    return this.uploadFile(file, '/uploadcsv');
  }

  uploadExcelFile(file: File): Observable<HttpEvent<any>> {
    return this.uploadFile(file, '/uploadexcel');
  }

  private uploadFile(file: File, endpoint: string): Observable<HttpEvent<any>> {
    const formData = new FormData();
    formData.append('multipartFile', file);

    const req = new HttpRequest('POST', `${this.baseUrl}${endpoint}`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }

  getData(): Observable<Entity[]> {
    return this.http.get<Entity[]>(`${this.baseUrl}/all`);
  }
}
