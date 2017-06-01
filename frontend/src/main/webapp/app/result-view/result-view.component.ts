import { Component } from '@angular/core';
import { Snippet } from '../snippet/snippet';

@Component({
    selector: `jhi-result-view`,
    // template: `<h1>{{ result }}</h1>`,
    // templateUrl: './resultView.component.html',
    template: `
    <div *ngIf="snippet">
      <h2>{{snippet.title}} title!</h2>
      <div><label>author: </label>{{snippet.author}}</div>
    </div> 
    `
})

export class ResultViewComponent {
    // result = 'result view works!';
    snippet: Snippet;
}
