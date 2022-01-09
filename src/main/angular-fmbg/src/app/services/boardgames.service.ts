import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Boardgames } from '../models/boardgames';

const baseUrl = 'http://localhost:8080/api/finder';

@Injectable({
  providedIn: 'root'
})
export class BoardgamesService {

  constructor(private http: HttpClient) { 
  }

  findGames(): Observable<Boardgames[]> {
    return this.http.get<Boardgames[]>(baseUrl);
  }
}
