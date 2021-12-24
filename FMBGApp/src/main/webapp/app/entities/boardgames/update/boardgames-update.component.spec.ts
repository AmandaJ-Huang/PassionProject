jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { BoardgamesService } from '../service/boardgames.service';
import { IBoardgames, Boardgames } from '../boardgames.model';

import { BoardgamesUpdateComponent } from './boardgames-update.component';

describe('Boardgames Management Update Component', () => {
  let comp: BoardgamesUpdateComponent;
  let fixture: ComponentFixture<BoardgamesUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let boardgamesService: BoardgamesService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [BoardgamesUpdateComponent],
      providers: [FormBuilder, ActivatedRoute],
    })
      .overrideTemplate(BoardgamesUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BoardgamesUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    boardgamesService = TestBed.inject(BoardgamesService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const boardgames: IBoardgames = { id: 456 };

      activatedRoute.data = of({ boardgames });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(boardgames));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Boardgames>>();
      const boardgames = { id: 123 };
      jest.spyOn(boardgamesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ boardgames });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: boardgames }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(boardgamesService.update).toHaveBeenCalledWith(boardgames);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Boardgames>>();
      const boardgames = new Boardgames();
      jest.spyOn(boardgamesService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ boardgames });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: boardgames }));
      saveSubject.complete();

      // THEN
      expect(boardgamesService.create).toHaveBeenCalledWith(boardgames);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Boardgames>>();
      const boardgames = { id: 123 };
      jest.spyOn(boardgamesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ boardgames });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(boardgamesService.update).toHaveBeenCalledWith(boardgames);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
