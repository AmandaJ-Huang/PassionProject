import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'boardgames',
        data: { pageTitle: 'fmbgApp.boardgames.home.title' },
        loadChildren: () => import('./boardgames/boardgames.module').then(m => m.BoardgamesModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
