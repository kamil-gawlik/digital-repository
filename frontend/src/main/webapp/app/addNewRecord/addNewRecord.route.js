"use strict";
var shared_1 = require('../shared');
var addNewRecord_component_1 = require('./addNewRecord.component');
exports.addNewRecordRoute = {
    path: 'addNewRecord',
    component: addNewRecord_component_1.AddNewRecordComponent,
    data: {
        authorities: [],
        pageTitle: 'Add new record'
    },
    canActivate: [shared_1.UserRouteAccessService]
};
