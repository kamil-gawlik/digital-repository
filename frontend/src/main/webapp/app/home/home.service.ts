
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
        console.log(formData);
        formData.append('filesList', record.file);
        console.log(formData);
        formData.append('recordName', record.recordName);
        console.log(formData);
        formData.append('author', record.author);
        console.log(formData);
        formData.append('description', record.description);
        console.log(formData);

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
