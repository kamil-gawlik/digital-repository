
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
        let formData = new FormData();
        if (record.files.length > 0 ) {
           for (let item of record.files) {
            formData.append('filesList', item, item.name);
            } 
        } else {
            let body = JSON.stringify( record );
            let headers = new Headers({ 'Content-Type': 'multipart/form-data' });
            let options = new RequestOptions({ headers: headers });
            this.http.post(this.recordUrl, body, options).subscribe();
            return
        }

        formData.append('recordName', record.recordName);
        formData.append('author', record.author);
        formData.append('description', record.description);

        let headers = new Headers();
        headers.append('Content-Type', 'multipart/form-data');
        let options = new RequestOptions({headers: headers});
        this.http.post(this.recordUrl, formData, options).subscribe(
            data => {
                console.log(data.json());
            }
        );
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
