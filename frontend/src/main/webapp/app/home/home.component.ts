import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';
import { Http } from '@angular/http';

import { Account, LoginModalService, Principal } from '../shared';
import { Record } from '../shared/record.model';
import { RecordService } from './home.service';
import { CitationType } from './home.citationType';

import { Snippet } from '../snippet/snippet';
// import { Ng2PopupModule } from 'ng2-popup';

// import { ResultViewComponent } from '../result-view/result-view.component';

// const SNIPPET: Snippet = { title: 'pierwszy', author: 'marcin' };

const SNIPPET_1: Snippet = { name: 'obiekt muzyczny', about: 'piosenka', author: 'marcin' };
const SNIPPET_2: Snippet = { name: 'obiekt wideo', about: 'film', author: 'wojtek' };
const SNIPPET_3: Snippet = { name: 'kod zrodlowy', about: 'program', author: 'mikolaj' };



@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: [
        'home.css'
    ]

})

export class HomeComponent implements OnInit {

    private isOn: boolean = false;
    private showDetailsIsOn: boolean = false;
    private detailsName: string;

    account: Account;
    record: Record;
    modalRef: NgbModalRef;
    http: Http;
    files: File[] = [];
    snippets: Snippet[] = [SNIPPET_1, SNIPPET_2, SNIPPET_3];

    getButtonText(): string {
        return ` ${this.isOn ? 'Go Back' : 'Search'}`;
    }

    getDetails(): string {
        return ` ${this.showDetailsIsOn ? 'Hide details' : 'Show details'}`;
    }

    setState(): void {
        this.isOn = !this.isOn;
    }

    showDetails(snippetName: string): void {
        this.showDetailsIsOn = !this.showDetailsIsOn;
        this.detailsName = snippetName;
    }

    citationType = CitationType;
    isAddingFile = false;

    constructor(
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: EventManager,
        private recordService: RecordService,
    ) {
        this.record = new Record();
    }

    addFileWithTag(citationType: CitationType) {
        switch (citationType) {
            case CitationType.Article: 
                console.log('PYKA 1');
                break;
            case CitationType.AudioObject:
                console.log('PYKA TEJ 2');
                break;
            case CitationType.Book:
                console.log('PYKA TEJ 3');
                break;
            case CitationType.CreativeWork:
                console.log('PYKA TEJ 4');
                break;
            case CitationType.ImageObject:
                console.log('PYKA TEJ 5');
                break;
            case CitationType.MusicRecording: 
                console.log('PYKA TEJ 6');
                break;
            case CitationType.SoftwareSourceCode:
                console.log('PYKA TEJ 7');
                break;
            case CitationType.VideoObject:
                console.log('PYKA TEJ 8');
                break;
        }
    }

    newRecord(event) {
        console.log(typeof this.recordService);
        this.recordService.addRecord(this.record);
    }

    fileChange(event: any, input: any) {
        this.isAddingFile = true;
        this.files.push(event.target.files[0]);
        this.record.file = event.target.files[0];
        let files = [].slice.call(event.target.files);
        this.record.filesList = files;
        input.value = this.files.map(f => f.name).join(', ');
        for (let item of this.files) {
            console.log(item.name);
        }
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
