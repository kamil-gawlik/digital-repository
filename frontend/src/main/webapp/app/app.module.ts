import './vendor.ts';

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { Ng2Webstorage } from 'ng2-webstorage';

import { FrontendSharedModule, UserRouteAccessService } from './shared';
import { FrontendHomeModule } from './home/home.module';
import { FrontendAdminModule } from './admin/admin.module';
import { FrontendAccountModule } from './account/account.module';
import { FrontendEntityModule } from './entities/entity.module';

import { LayoutRoutingModule } from './layouts';
import { customHttpProvider } from './blocks/interceptor/http.provider';
import { PaginationConfig } from './blocks/config/uib-pagination.config';

import { FormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { ResultViewComponent } from './result-view/result-view.component';

import {
    JhiMainComponent,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ErrorComponent
} from './layouts';


@NgModule({
    imports: [
        BrowserModule,
        LayoutRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-' }),
        FrontendSharedModule,
        FrontendHomeModule,
        FrontendAdminModule,
        FrontendAccountModule,
        FrontendEntityModule,

        FormsModule
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        FooterComponent,

        HomeComponent,
        ResultViewComponent
    ],
    providers: [
        ProfileService,
        { provide: Window, useValue: window },
        { provide: Document, useValue: document },
        customHttpProvider(),
        PaginationConfig,
        UserRouteAccessService
    ],
    bootstrap: [JhiMainComponent]
})
export class FrontendAppModule { }
