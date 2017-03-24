import { Route } from '@angular/router';

import { UserRouteAccessService } from '../shared';
import { AddNewRecordComponent } from './addNewRecord.component';

export const addNewRecordRoute: Route = {
  path: 'addNewRecord',
  component: AddNewRecordComponent,
  data: {
    authorities: [],
    pageTitle: 'Add new record'
  },
  canActivate: [UserRouteAccessService]
};
