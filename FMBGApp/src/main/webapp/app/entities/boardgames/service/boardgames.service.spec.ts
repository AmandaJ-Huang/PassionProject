import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IBoardgames, Boardgames } from '../boardgames.model';

import { BoardgamesService } from './boardgames.service';

describe('Boardgames Service', () => {
  let service: BoardgamesService;
  let httpMock: HttpTestingController;
  let elemDefault: IBoardgames;
  let expectedResult: IBoardgames | IBoardgames[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(BoardgamesService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      thumbnail_url: 'AAAAAAA',
      primary_name: 'AAAAAAA',
      minplayers: 0,
      maxplayers: 0,
      suggested_numplayers: 0,
      playingtime: 0,
      suggested_playerage: 0,
      rating: 0,
      rank: 0,
      averageweight: 0,
      category: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a Boardgames', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new Boardgames()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Boardgames', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          thumbnail_url: 'BBBBBB',
          primary_name: 'BBBBBB',
          minplayers: 1,
          maxplayers: 1,
          suggested_numplayers: 1,
          playingtime: 1,
          suggested_playerage: 1,
          rating: 1,
          rank: 1,
          averageweight: 1,
          category: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Boardgames', () => {
      const patchObject = Object.assign(
        {
          thumbnail_url: 'BBBBBB',
          maxplayers: 1,
          suggested_numplayers: 1,
          averageweight: 1,
          category: 'BBBBBB',
        },
        new Boardgames()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Boardgames', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          thumbnail_url: 'BBBBBB',
          primary_name: 'BBBBBB',
          minplayers: 1,
          maxplayers: 1,
          suggested_numplayers: 1,
          playingtime: 1,
          suggested_playerage: 1,
          rating: 1,
          rank: 1,
          averageweight: 1,
          category: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a Boardgames', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addBoardgamesToCollectionIfMissing', () => {
      it('should add a Boardgames to an empty array', () => {
        const boardgames: IBoardgames = { id: 123 };
        expectedResult = service.addBoardgamesToCollectionIfMissing([], boardgames);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(boardgames);
      });

      it('should not add a Boardgames to an array that contains it', () => {
        const boardgames: IBoardgames = { id: 123 };
        const boardgamesCollection: IBoardgames[] = [
          {
            ...boardgames,
          },
          { id: 456 },
        ];
        expectedResult = service.addBoardgamesToCollectionIfMissing(boardgamesCollection, boardgames);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Boardgames to an array that doesn't contain it", () => {
        const boardgames: IBoardgames = { id: 123 };
        const boardgamesCollection: IBoardgames[] = [{ id: 456 }];
        expectedResult = service.addBoardgamesToCollectionIfMissing(boardgamesCollection, boardgames);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(boardgames);
      });

      it('should add only unique Boardgames to an array', () => {
        const boardgamesArray: IBoardgames[] = [{ id: 123 }, { id: 456 }, { id: 91690 }];
        const boardgamesCollection: IBoardgames[] = [{ id: 123 }];
        expectedResult = service.addBoardgamesToCollectionIfMissing(boardgamesCollection, ...boardgamesArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const boardgames: IBoardgames = { id: 123 };
        const boardgames2: IBoardgames = { id: 456 };
        expectedResult = service.addBoardgamesToCollectionIfMissing([], boardgames, boardgames2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(boardgames);
        expect(expectedResult).toContain(boardgames2);
      });

      it('should accept null and undefined values', () => {
        const boardgames: IBoardgames = { id: 123 };
        expectedResult = service.addBoardgamesToCollectionIfMissing([], null, boardgames, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(boardgames);
      });

      it('should return initial array if no Boardgames is added', () => {
        const boardgamesCollection: IBoardgames[] = [{ id: 123 }];
        expectedResult = service.addBoardgamesToCollectionIfMissing(boardgamesCollection, undefined, null);
        expect(expectedResult).toEqual(boardgamesCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
