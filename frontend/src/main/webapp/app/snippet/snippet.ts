const enum Type {
    Article,
    ImageObject,
    VideoObject,
    AudioObject,
    MusicRecording,
    SoftwareSourceCode
}

const enum ProgrammingLanguage {
    "Java",
    "C#",
    "other"
}
const enum RuntimePlatform {
    "other"
}

export class Snippet {
    type?: Type;
    name: string;
    about: string;
    author: string;
    givenName?: string;
    additionalName?: string;
    familyName?: string;
    dateCreated?: string;
    fileFormat?: string;
    caption?: string;
    exifData?: string;
    isbn?: number;
    numberOfPages?: number;
    bookEdition?: string;
    articleSection?: string;
    codeRepository?: URL;
    programmingLanguage?: ProgrammingLanguage;
    runtimePlatform?: RuntimePlatform;
    byArtist?: string;
    inAlbum?: string;
    duration?: string;
    actorGivenName?: string;
    actorAdditionalName?: string;
    actorFamilyName?: string;
    directorGivenName?: string;
    directorAdditionalName?: string;
    directorFamilyName?: string;
    videoFrameSize?: string;
}