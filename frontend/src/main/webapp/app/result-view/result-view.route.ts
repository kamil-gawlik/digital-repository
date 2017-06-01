import { Route } from '@angular/router';
import { UserRouteAccessService } from '../shared';

import { ResultViewComponent } from './result-view.component';

export const resultViewRoute: Route = {
    path: 'result-view',
    component: ResultViewComponent,
    data: {
        authorities: [],
        pageTitle: 'Result View'
    },
    canActivate: [UserRouteAccessService]
};