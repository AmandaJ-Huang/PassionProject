import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { BoardgamesComponent } from '../list/boardgames.component';
import { BoardgamesDetailComponent } from '../detail/boardgames-detail.component';
import { BoardgamesUpdateComponent } from '../update/boardgames-update.component';
import { BoardgamesRoutingResolveService } from './boardgames-routing-resolve.service';

const boardgamesRoute: Routes = [
  {
    path: '',
    component: BoardgamesComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BoardgamesDetailComponent,
    resolve: {
      boardgames: BoardgamesRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BoardgamesUpdateComponent,
    resolve: {
      boardgames: BoardgamesRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BoardgamesUpdateComponent,
    resolve: {
      boardgames: BoardgamesRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(boardgamesRoute)],
  exports: [RouterModule],
})
export class BoardgamesRoutingModule {}
