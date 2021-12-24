import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IBoardgames } from '../boardgames.model';
import { BoardgamesService } from '../service/boardgames.service';

@Component({
  templateUrl: './boardgames-delete-dialog.component.html',
})
export class BoardgamesDeleteDialogComponent {
  boardgames?: IBoardgames;

  constructor(protected boardgamesService: BoardgamesService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.boardgamesService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
