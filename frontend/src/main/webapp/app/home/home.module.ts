import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FrontendSharedModule } from '../shared';

import { HOME_ROUTE } from './';
import { RecordService } from './home.service';

import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

// do implementacji pop-up'ow /merq
// import { Ng2PopupModule } from 'ng2-popup';

@NgModule({
    imports: [
        FrontendSharedModule,
        RouterModule.forRoot([HOME_ROUTE], { useHash: true }),
        BrowserModule,
        FormsModule
    ],
    declarations: [
    ],
    entryComponents: [
    ],
    providers: [
        RecordService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FrontendHomeModule { }
