export class Record {
    public recordName?: string;
    public author?: string;
    public description?: string;
    public file?: File;
    public filesList: File[] = [];
    constructor() {}
}
