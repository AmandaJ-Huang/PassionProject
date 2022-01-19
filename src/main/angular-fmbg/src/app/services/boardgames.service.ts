import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
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

  findGames(params: any): Observable<Boardgames[]> {
    let fullUrl = this.baseUrl + '/api/finder?' + params;
    return this.http.get<Boardgames[]>(fullUrl);
  }
}
