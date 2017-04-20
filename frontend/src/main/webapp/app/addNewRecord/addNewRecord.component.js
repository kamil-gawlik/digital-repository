"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
var AddNewRecordComponent = (function () {
    function AddNewRecordComponent(loginModalService, registerService, elementRef, renderer) {
        this.loginModalService = loginModalService;
        this.registerService = registerService;
        this.elementRef = elementRef;
        this.renderer = renderer;
    }
    AddNewRecordComponent.prototype.ngOnInit = function () {
        this.success = false;
        this.registerAccount = {};
    };
    AddNewRecordComponent.prototype.ngAfterViewInit = function () {
        this.renderer.invokeElementMethod(this.elementRef.nativeElement.querySelector('#login'), 'focus', []);
    };
    AddNewRecordComponent.prototype.register = function () {
        var _this = this;
        if (this.registerAccount.password !== this.confirmPassword) {
            this.doNotMatch = 'ERROR';
        }
        else {
            this.doNotMatch = null;
            this.error = null;
            this.errorUserExists = null;
            this.errorEmailExists = null;
            this.registerAccount.langKey = 'en';
            this.registerService.save(this.registerAccount).subscribe(function () {
                _this.success = true;
            }, function (response) { return _this.processError(response); });
        }
    };
    AddNewRecordComponent.prototype.openLogin = function () {
        this.modalRef = this.loginModalService.open();
    };
    AddNewRecordComponent.prototype.processError = function (response) {
        this.success = null;
        if (response.status === 400 && response._body === 'login already in use') {
            this.errorUserExists = 'ERROR';
        }
        else if (response.status === 400 && response._body === 'e-mail address already in use') {
            this.errorEmailExists = 'ERROR';
        }
        else {
            this.error = 'ERROR';
        }
    };
    AddNewRecordComponent = __decorate([
        core_1.Component({
            selector: 'jhi-addNewRecord',
            templateUrl: './addNewRecord.component.html'
        })
    ], AddNewRecordComponent);
    return AddNewRecordComponent;
}());
exports.AddNewRecordComponent = AddNewRecordComponent;
