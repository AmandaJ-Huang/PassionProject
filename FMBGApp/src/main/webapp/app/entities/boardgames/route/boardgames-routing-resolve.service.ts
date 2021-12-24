import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IBoardgames, Boardgames } from '../boardgames.model';
import { BoardgamesService } from '../service/boardgames.service';

@Injectable({ providedIn: 'root' })
export class BoardgamesRoutingResolveService implements Resolve<IBoardgames> {
  constructor(protected service: BoardgamesService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBoardgames> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((boardgames: HttpResponse<Boardgames>) => {
          if (boardgames.body) {
            return of(boardgames.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Boardgames());
  }
}
