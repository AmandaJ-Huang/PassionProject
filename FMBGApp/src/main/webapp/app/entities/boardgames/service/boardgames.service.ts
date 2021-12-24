import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBoardgames, getBoardgamesIdentifier } from '../boardgames.model';

export type EntityResponseType = HttpResponse<IBoardgames>;
export type EntityArrayResponseType = HttpResponse<IBoardgames[]>;

@Injectable({ providedIn: 'root' })
export class BoardgamesService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/boardgames');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(boardgames: IBoardgames): Observable<EntityResponseType> {
    return this.http.post<IBoardgames>(this.resourceUrl, boardgames, { observe: 'response' });
  }

  update(boardgames: IBoardgames): Observable<EntityResponseType> {
    return this.http.put<IBoardgames>(`${this.resourceUrl}/${getBoardgamesIdentifier(boardgames) as number}`, boardgames, {
      observe: 'response',
    });
  }

  partialUpdate(boardgames: IBoardgames): Observable<EntityResponseType> {
    return this.http.patch<IBoardgames>(`${this.resourceUrl}/${getBoardgamesIdentifier(boardgames) as number}`, boardgames, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBoardgames>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBoardgames[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addBoardgamesToCollectionIfMissing(
    boardgamesCollection: IBoardgames[],
    ...boardgamesToCheck: (IBoardgames | null | undefined)[]
  ): IBoardgames[] {
    const boardgames: IBoardgames[] = boardgamesToCheck.filter(isPresent);
    if (boardgames.length > 0) {
      const boardgamesCollectionIdentifiers = boardgamesCollection.map(boardgamesItem => getBoardgamesIdentifier(boardgamesItem)!);
      const boardgamesToAdd = boardgames.filter(boardgamesItem => {
        const boardgamesIdentifier = getBoardgamesIdentifier(boardgamesItem);
        if (boardgamesIdentifier == null || boardgamesCollectionIdentifiers.includes(boardgamesIdentifier)) {
          return false;
        }
        boardgamesCollectionIdentifiers.push(boardgamesIdentifier);
        return true;
      });
      return [...boardgamesToAdd, ...boardgamesCollection];
    }
    return boardgamesCollection;
  }
}
