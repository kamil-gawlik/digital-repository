"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
var router_1 = require('@angular/router');
var shared_1 = require('../shared');
var _1 = require('./');
var home_service_1 = require("./home.service");
var FrontendHomeModule = (function () {
    function FrontendHomeModule() {
    }
    FrontendHomeModule = __decorate([
        core_1.NgModule({
            imports: [
                shared_1.FrontendSharedModule,
                router_1.RouterModule.forRoot([_1.HOME_ROUTE], { useHash: true })
            ],
            declarations: [
                _1.HomeComponent,
            ],
            entryComponents: [],
            providers: [
                home_service_1.RecordService
            ],
            schemas: [core_1.CUSTOM_ELEMENTS_SCHEMA]
        })
    ], FrontendHomeModule);
    return FrontendHomeModule;
}());
exports.FrontendHomeModule = FrontendHomeModule;
