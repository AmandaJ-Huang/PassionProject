jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IBoardgames, Boardgames } from '../boardgames.model';
import { BoardgamesService } from '../service/boardgames.service';

import { BoardgamesRoutingResolveService } from './boardgames-routing-resolve.service';

describe('Boardgames routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: BoardgamesRoutingResolveService;
  let service: BoardgamesService;
  let resultBoardgames: IBoardgames | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [Router, ActivatedRouteSnapshot],
    });
    mockRouter = TestBed.inject(Router);
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
    routingResolveService = TestBed.inject(BoardgamesRoutingResolveService);
    service = TestBed.inject(BoardgamesService);
    resultBoardgames = undefined;
  });

  describe('resolve', () => {
    it('should return IBoardgames returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBoardgames = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultBoardgames).toEqual({ id: 123 });
    });

    it('should return new IBoardgames if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBoardgames = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultBoardgames).toEqual(new Boardgames());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as Boardgames })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBoardgames = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultBoardgames).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
