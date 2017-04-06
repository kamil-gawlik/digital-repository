
import {Injectable}   from '@angular/core';
import {Http, Response} from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import {Observable} from 'rxjs/Observable';

import {Record} from '../shared/record.model';

@Injectable()
export class RecordService {

    private recordUrl = 'http://localhost:8081/api/upload';

    constructor(private http: Http) { }

    addRecord(record: Record) {
        console.log('RecordService.addRecord executed.');
        console.log(record.author);
        console.log(record.description);
        let body = JSON.stringify( record );
        let headers = new Headers({ 'Content-Type': 'application/form-data' });
        let options = new RequestOptions({ headers: headers });
        console.log(body);

        this.http.post(this.recordUrl, body, options).subscribe();
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
