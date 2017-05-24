import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MdDialogModule } from '@angular/material';

import { FrontendSharedModule } from '../shared';

import { HOME_ROUTE, HomeComponent, DialogComponent } from './';
import {RecordService} from './home.service';


@NgModule({
    imports: [
        FrontendSharedModule,
        RouterModule.forRoot([ HOME_ROUTE ], { useHash: true }),
        MdDialogModule
    ],
    declarations: [
        HomeComponent
    ],
    entryComponents: [

    ],
    providers: [
        RecordService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FrontendHomeModule {}
