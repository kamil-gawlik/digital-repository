"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
var http_1 = require('@angular/http');
var Observable_1 = require('rxjs/Observable');
var RecordService = (function () {
    function RecordService(http) {
        this.http = http;
        this.recordUrl = 'localhost:8081/api/publications';
    }
    RecordService.prototype.addRecord = function (record) {
        console.log("RecordService.addRecord ");
        var body = JSON.stringify(record);
        var headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        var options = new http_1.RequestOptions({ headers: headers });
        return this.http.post(this.recordUrl, body, options)
            .map(this.extractData)
            .catch(this.handleError);
    };
    RecordService.prototype.extractData = function (res) {
        var body = res.json();
        return body.data || {};
    };
    RecordService.prototype.handleError = function (error) {
        console.error(error);
        return Observable_1.Observable.throw(error.json().error || 'Server Error');
    };
    RecordService = __decorate([
        core_1.Injectable()
    ], RecordService);
    return RecordService;
}());
exports.RecordService = RecordService;
