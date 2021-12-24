import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { BoardgamesComponent } from './list/boardgames.component';
import { BoardgamesDetailComponent } from './detail/boardgames-detail.component';
import { BoardgamesUpdateComponent } from './update/boardgames-update.component';
import { BoardgamesDeleteDialogComponent } from './delete/boardgames-delete-dialog.component';
import { BoardgamesRoutingModule } from './route/boardgames-routing.module';

@NgModule({
  imports: [SharedModule, BoardgamesRoutingModule],
  declarations: [BoardgamesComponent, BoardgamesDetailComponent, BoardgamesUpdateComponent, BoardgamesDeleteDialogComponent],
  entryComponents: [BoardgamesDeleteDialogComponent],
})
export class BoardgamesModule {}
