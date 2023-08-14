import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UploadService } from '../upload.service';
import { Entity } from '../Entity';
@Component({
  selector: 'app-list-of-dtata',
  templateUrl: './list-of-dtata.component.html',
  styleUrls: ['./list-of-dtata.component.css']
})
export class ListOfDtataComponent implements OnInit{
  progress: number = 0;
  message: string = '';
  fetchedData!: Entity[];

  constructor(private service: UploadService) {}
  ngOnInit(): void {
    this.fetchData()
  }

  onFileSelected(event: any) {
    // Handle file selection logic
  }

  onUpload() {
    // Handle file upload logic
  }

  fetchData() {
    this.service.getData().subscribe(data => {
      this.fetchedData = data; // Assign fetched data to the property, not the function
    });
  }
  
}
