
import {Injectable}   from '@angular/core';
import {Http, Response} from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import {Observable} from 'rxjs/Observable';

import {Record} from '../shared/record.model';

@Injectable()
export class RecordService {

    private recordUrl = 'localhost:8081/api/publications';

    constructor(private http: Http) { }

    addRecord(record: Record) {
        console.log('RecordService.addRecord executed.');
        let body = JSON.stringify( record );
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        return this.http.post(this.recordUrl, body, options)
            .map(this.extractData)
            .catch(this.handleError);
    }

    private extractData(res: Response) {
        let body = res.json();
        return body.data || {};
    }

    private handleError(error: Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server Error');
    }
}
