import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager} from 'ng-jhipster';
import { Http } from '@angular/http';

import { Account, LoginModalService, Principal} from '../shared';
import { Record } from '../shared/record.model';
import { RecordService } from './home.service';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: [
        'home.css'
    ]

})
export class HomeComponent implements OnInit {
    account: Account;
    record: Record;
    modalRef: NgbModalRef;
    http: Http;

    constructor(
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: EventManager,
        private recordService: RecordService,
    ) {
        this.record = new Record();
    }

    newRecord(event) {
        console.log(typeof this.recordService);
        this.recordService.addRecord(this.record);
    }

    fileChange(e) {
        this.record.file = e.target.files[0];
        console.log(this.record.file);
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', (message) => {
            this.principal.identity().then((account) => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }
}
