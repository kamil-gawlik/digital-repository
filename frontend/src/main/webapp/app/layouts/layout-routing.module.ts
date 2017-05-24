import { NgModule } from '@angular/core';
import { RouterModule, Routes, Resolve } from '@angular/router';

import { navbarRoute, ADD_RECORD_ROUTE } from '../app.route';
import { errorRoute } from './';

let LAYOUT_ROUTES = [
    navbarRoute,
    ADD_RECORD_ROUTE,
    ...errorRoute
];

@NgModule({
  imports: [
    RouterModule.forRoot([LAYOUT_ROUTES], { useHash: true })
  ],
  exports: [
    RouterModule
  ]
})
export class LayoutRoutingModule {}
