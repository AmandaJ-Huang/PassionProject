import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Boardgames } from '../models/boardgames';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BoardgamesService {

  baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) { 
  }

  findGames(): Observable<Boardgames[]> {
    return this.http.get<Boardgames[]>(this.baseUrl + '/api/finder?');
  }
}
