export class Record {
    public recordName?: string;
    public user?: string;
    public description?: string;
    public file?: File;
    public filesList: File[] = [];
    constructor() {}
}
