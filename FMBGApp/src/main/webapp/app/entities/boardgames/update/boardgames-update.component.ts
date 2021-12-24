import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IBoardgames, Boardgames } from '../boardgames.model';
import { BoardgamesService } from '../service/boardgames.service';

@Component({
  selector: 'jhi-boardgames-update',
  templateUrl: './boardgames-update.component.html',
})
export class BoardgamesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    thumbnail_url: [],
    primary_name: [],
    minplayers: [],
    maxplayers: [],
    suggested_numplayers: [],
    playingtime: [],
    suggested_playerage: [],
    rating: [],
    rank: [],
    averageweight: [],
    category: [],
  });

  constructor(protected boardgamesService: BoardgamesService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ boardgames }) => {
      this.updateForm(boardgames);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const boardgames = this.createFromForm();
    if (boardgames.id !== undefined) {
      this.subscribeToSaveResponse(this.boardgamesService.update(boardgames));
    } else {
      this.subscribeToSaveResponse(this.boardgamesService.create(boardgames));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBoardgames>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(boardgames: IBoardgames): void {
    this.editForm.patchValue({
      id: boardgames.id,
      thumbnail_url: boardgames.thumbnail_url,
      primary_name: boardgames.primary_name,
      minplayers: boardgames.minplayers,
      maxplayers: boardgames.maxplayers,
      suggested_numplayers: boardgames.suggested_numplayers,
      playingtime: boardgames.playingtime,
      suggested_playerage: boardgames.suggested_playerage,
      rating: boardgames.rating,
      rank: boardgames.rank,
      averageweight: boardgames.averageweight,
      category: boardgames.category,
    });
  }

  protected createFromForm(): IBoardgames {
    return {
      ...new Boardgames(),
      id: this.editForm.get(['id'])!.value,
      thumbnail_url: this.editForm.get(['thumbnail_url'])!.value,
      primary_name: this.editForm.get(['primary_name'])!.value,
      minplayers: this.editForm.get(['minplayers'])!.value,
      maxplayers: this.editForm.get(['maxplayers'])!.value,
      suggested_numplayers: this.editForm.get(['suggested_numplayers'])!.value,
      playingtime: this.editForm.get(['playingtime'])!.value,
      suggested_playerage: this.editForm.get(['suggested_playerage'])!.value,
      rating: this.editForm.get(['rating'])!.value,
      rank: this.editForm.get(['rank'])!.value,
      averageweight: this.editForm.get(['averageweight'])!.value,
      category: this.editForm.get(['category'])!.value,
    };
  }
}
