import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BgServiceService {
  private bgUrl: string;

  constructor(private http: HttpClient) { 
    this.bgUrl = 'http://localhost:8080/finder';
  }

  // TODO: create finder method
}
