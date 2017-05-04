import { Component, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef } from '@angular/material';
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
    dialogRef: MdDialogRef<any>;
    fileForm: MdDialog;
    http: Http;
    files: File[] = [];

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

    fileChange(event: any, input: any) {
        this.files.push(event.target.files[0]);
        this.record.file = event.target.files[0];
        let files = [].slice.call(event.target.files);
        this.record.filesList = files;
        input.value = this.files.map(f => f.name).join(', ');
        for (let item of this.files) {
            console.log(item.name);
        }
        this.dialogRef = this.fileForm.open(DialogComponent);
        this.dialogRef.afterClosed().subscribe(result => {
            this.dialogRef = null;
    });
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

@Component({
  selector: 'jhi-your-dialog-selector',
  template: `
  <h2>Hi! I am modal dialog!</h2>
  <button md-raised-button (click)="dialogRef.close()">Close dialog</button>`
})

export class DialogComponent {
  constructor(public dialogRef: MdDialogRef<any>) { }
}
