import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Http } from '@angular/http';

import { ProfileService } from '../profiles/profile.service'; // FIXME barrel doesnt work here
import { Principal, LoginModalService, LoginService } from '../../shared';

import { VERSION, DEBUG_INFO_ENABLED } from '../../app.constants';
import { Search } from '../../shared/search.model';
import { SearchService } from './navbar.service'

@Component({
    selector: 'jhi-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: [
        'navbar.css'
    ]
})
export class NavbarComponent implements OnInit {

    inProduction: boolean;
    isNavbarCollapsed: boolean;
    languages: any[];
    swaggerEnabled: boolean;
    modalRef: NgbModalRef;
    version: string;
    search: Search;
    http: Http;

    constructor(
        private loginService: LoginService,
        private principal: Principal,
        private loginModalService: LoginModalService,
        private profileService: ProfileService,
        private router: Router
    ) {
        this.version = DEBUG_INFO_ENABLED ? 'v' + VERSION : '';
        this.isNavbarCollapsed = true;
        this.search = new Search();
    }

    searchRecords(event){
        console.log(typeof this.search)
    }
    ngOnInit() {

        this.profileService.getProfileInfo().subscribe(profileInfo => {
            this.inProduction = profileInfo.inProduction;
            this.swaggerEnabled = profileInfo.swaggerEnabled;
        });
    }


    collapseNavbar() {
        this.isNavbarCollapsed = true;
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    logout() {
        this.collapseNavbar();
        this.loginService.logout();
        this.router.navigate(['']);
    }

    toggleNavbar() {
        this.isNavbarCollapsed = !this.isNavbarCollapsed;
    }

    getImageUrl() {
        return this.isAuthenticated() ? this.principal.getImageUrl() : null;
    }
}
