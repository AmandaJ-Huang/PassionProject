import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Boardgames } from '../models/boardgames';

@Injectable({
  providedIn: 'root'
})
export class BoardgamesService {
  private bgUrl: string;

  constructor(private http: HttpClient) { 
    this.bgUrl = 'http://localhost:8080/finder';
  }

  public findGames(): Observable<Boardgames[]> {
    return this.http.get<Boardgames[]>(this.bgUrl);
  }
}
