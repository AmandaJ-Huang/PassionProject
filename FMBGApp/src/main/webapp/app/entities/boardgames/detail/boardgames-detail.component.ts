import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBoardgames } from '../boardgames.model';

@Component({
  selector: 'jhi-boardgames-detail',
  templateUrl: './boardgames-detail.component.html',
})
export class BoardgamesDetailComponent implements OnInit {
  boardgames: IBoardgames | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ boardgames }) => {
      this.boardgames = boardgames;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
