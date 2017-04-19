
import {Injectable}   from '@angular/core';
import {Http, Response} from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import {Observable} from 'rxjs/Observable';

import {Record} from '../shared/record.model';

@Injectable()
export class RecordService {

    private recordUrl = 'http://localhost:8080/backend/api/upload';

    constructor(private http: Http) { }

    addRecord(record: Record) {
        let formData = new FormData();
        if (record.filesList.length > 0 ) {
           for (let item of record.filesList) {
            formData.append('filesList', item, item.name);
            } 
        } 
        
        formData.append('recordName', record.recordName);
        formData.append('author', record.author);
        formData.append('description', record.description);

        let headers = new Headers();
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
