import { Route } from '@angular/router';

import { NavbarComponent } from './layouts';
import { AddNewRecordComponent } from './add-new-record/add-new-record.component'
export const navbarRoute: Route = {
    path: '',
    component: NavbarComponent,
    outlet: 'navbar'
  };

export const ADD_RECORD_ROUTE: Route = {
    path: '/add-new-rec',
    component: AddNewRecordComponent,
    outlet: 'popup'
};

