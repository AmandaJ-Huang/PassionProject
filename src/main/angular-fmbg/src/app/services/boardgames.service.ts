import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Boardgames } from '../models/boardgames';

const baseUrl = 'https://fmbg.herokuapp.com/finder';

@Injectable({
  providedIn: 'root'
})
export class BoardgamesService {

  constructor(private http: HttpClient) { 
  }

  public findGames(): Observable<Boardgames[]> {
    return this.http.get<Boardgames[]>(baseUrl);
  }
}
