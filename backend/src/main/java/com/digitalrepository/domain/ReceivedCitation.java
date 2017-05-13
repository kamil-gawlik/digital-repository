package com.digitalrepository.domain;

import com.digitalrepository.domain.schemaorg.enums.CitationType;

/**
 * A class used to map the received JSON to an object.
 * Created by Piotrek on 10.05.2017.
 */
public class ReceivedCitation {

    /**
     * Fixed fields for every type of citation
     */
    private CitationType type;
    private String name;
    private String about;
    private String author;
    private SchemaOrgPerson creator;
    private String fileFormat;

    /**
     * Optional fields for Book
     */
    private String isbn;
    private String numberOfPages;
    private String bookEdition;

    /**
     * Optional fields for Article
     */
    private String articleSection;

    /**
     * Optional fields for SoftwareSourceCode
     */
    private String codeRepository;
    private String programmingLanguage;
    private String runtimePlatform;

    /**
     * Optional fields for MusicRecording
     */
    private String byArtist;
    private String inAlbum;
    private String duration;

    /**
     * Optional fields for VideoObject
     */
    private SchemaOrgPerson actor;
    private SchemaOrgPerson director;

    /**
     * Optional field common for ImageObject and VideoObject
     */
    private String caption;
    private String exifData;
    private String fileName;

    public ReceivedCitation(CitationType type,
                            String name,
                            String about,
                            String author,
                            SchemaOrgPerson creator,
                            String fileFormat,
                            String isbn,
                            String numberOfPages,
                            String bookEdition,
                            String articleSection,
                            String codeRepository,
                            String programmingLanguage,
                            String runtimePlatform,
                            String byArtist,
                            String inAlbum,
                            String duration,
                            SchemaOrgPerson actor,
                            SchemaOrgPerson director,
                            String caption) {
        this.type = type;
        this.name = name;
        this.about = about;
        this.author = author;
        this.creator = creator;
        this.fileFormat = fileFormat;
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
        this.bookEdition = bookEdition;
        this.articleSection = articleSection;
        this.codeRepository = codeRepository;
        this.programmingLanguage = programmingLanguage;
        this.runtimePlatform = runtimePlatform;
        this.byArtist = byArtist;
        this.inAlbum = inAlbum;
        this.duration = duration;
        this.actor = actor;
        this.director = director;
        this.caption = caption;
    }

    @Override
    public String toString() {
        return "{" +
            "\n\"type\":\"" + type + "\"" +
            "\n\"fileName\":\"" + fileName + "\"" +
            ",\n\"name\":\"" + name + "\"" +
            ",\n\"about\":\"" + about + "\"" +
            ",\n\"author\":\"" + author + "\"" +
            ",\n\"creator\":" + creator +
            ",\n\"fileFormat\":\"" + fileFormat + "\"" +
            ",\n\"isbn\":\"" + isbn + "\"" +
            ",\n\"numberOfPages\":\"" + numberOfPages + "\"" +
            ",\n\"bookEdition\":\"" + bookEdition + "\"" +
            ",\n\"articleSection\":\"" + articleSection + "\"" +
            ",\n\"codeRepository\":\"" + codeRepository + "\"" +
            ",\n\"programmingLanguage\":\"" + programmingLanguage + "\"" +
            ",\n\"runtimePlatform\":\"" + runtimePlatform + "\"" +
            ",\n\"byArtist\":\"" + byArtist + "\"" +
            ",\n\"inAlbum\":\"" + inAlbum + "\"" +
            ",\n\"duration\":\"" + duration + "\"" +
            ",\n\"actor\":" + actor +
            ",\n\"director\":" + director +
            ",\n\"caption\":\"" + caption + "\"" +
            "\n}";
    }

    public CitationType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }

    public String getAuthor() {
        return author;
    }

    public SchemaOrgPerson getCreator() {
        return creator;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getNumberOfPages() {
        return numberOfPages;
    }

    public String getBookEdition() {
        return bookEdition;
    }

    public String getArticleSection() {
        return articleSection;
    }

    public String getCodeRepository() {
        return codeRepository;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public String getRuntimePlatform() {
        return runtimePlatform;
    }

    public String getByArtist() {
        return byArtist;
    }

    public String getInAlbum() {
        return inAlbum;
    }

    public String getDuration() {
        return duration;
    }

    public SchemaOrgPerson getActor() {
        return actor;
    }

    public SchemaOrgPerson getDirector() {
        return director;
    }

    public String getCaption() {
        return caption;
    }

    public String getExifData() {return exifData; }

    public void setExifData(String exifData) {
        this.exifData = exifData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReceivedCitation citation = (ReceivedCitation) o;

        if (type != null ? !type.equals(citation.type) : citation.type != null) return false;
        if (name != null ? !name.equals(citation.name) : citation.name != null) return false;
        if (about != null ? !about.equals(citation.about) : citation.about != null) return false;
        if (author != null ? !author.equals(citation.author) : citation.author != null) return false;
        if (creator != null ? !creator.equals(citation.creator) : citation.creator != null) return false;
        if (fileFormat != null ? !fileFormat.equals(citation.fileFormat) : citation.fileFormat != null) return false;
        if (isbn != null ? !isbn.equals(citation.isbn) : citation.isbn != null) return false;
        if (numberOfPages != null ? !numberOfPages.equals(citation.numberOfPages) : citation.numberOfPages != null)
            return false;
        if (bookEdition != null ? !bookEdition.equals(citation.bookEdition) : citation.bookEdition != null)
            return false;
        if (articleSection != null ? !articleSection.equals(citation.articleSection) : citation.articleSection != null)
            return false;
        if (codeRepository != null ? !codeRepository.equals(citation.codeRepository) : citation.codeRepository != null)
            return false;
        if (programmingLanguage != null ? !programmingLanguage.equals(citation.programmingLanguage) : citation.programmingLanguage != null)
            return false;
        if (runtimePlatform != null ? !runtimePlatform.equals(citation.runtimePlatform) : citation.runtimePlatform != null)
            return false;
        if (byArtist != null ? !byArtist.equals(citation.byArtist) : citation.byArtist != null) return false;
        if (inAlbum != null ? !inAlbum.equals(citation.inAlbum) : citation.inAlbum != null) return false;
        if (duration != null ? !duration.equals(citation.duration) : citation.duration != null) return false;
        if (actor != null ? !actor.equals(citation.actor) : citation.actor != null) return false;
        if (director != null ? !director.equals(citation.director) : citation.director != null) return false;
        if (caption != null ? !caption.equals(citation.caption) : citation.caption != null) return false;
        return exifData != null ? exifData.equals(citation.exifData) : citation.exifData == null;
    }
}
