import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FrontendSharedModule } from '../shared';

import { HOME_ROUTE, HomeComponent } from './';
import {RecordService} from './home.service';


@NgModule({
    imports: [
        FrontendSharedModule,
        RouterModule.forRoot([ HOME_ROUTE ], { useHash: true })
    ],
    declarations: [
        HomeComponent,
    ],
    entryComponents: [
    ],
    providers: [
        RecordService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FrontendHomeModule {}
