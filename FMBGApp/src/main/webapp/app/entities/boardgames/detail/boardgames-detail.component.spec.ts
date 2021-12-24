import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BoardgamesDetailComponent } from './boardgames-detail.component';

describe('Boardgames Management Detail Component', () => {
  let comp: BoardgamesDetailComponent;
  let fixture: ComponentFixture<BoardgamesDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BoardgamesDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ boardgames: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(BoardgamesDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(BoardgamesDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load boardgames on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.boardgames).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
