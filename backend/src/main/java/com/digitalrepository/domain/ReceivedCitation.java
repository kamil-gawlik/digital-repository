package com.digitalrepository.domain;

import com.digitalrepository.domain.schemaorg.SchemaOrgPerson;

/**
 * A class used to map the received JSON to an object.
 * Created by Piotrek on 10.05.2017.
 */
public class ReceivedCitation {

    /**
     * Fixed fields for every type of citation
     */
    private String type;
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

    public ReceivedCitation(String type,
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public SchemaOrgPerson getCreator() {
        return creator;
    }

    public void setCreator(SchemaOrgPerson creator) {
        this.creator = creator;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(String numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getBookEdition() {
        return bookEdition;
    }

    public void setBookEdition(String bookEdition) {
        this.bookEdition = bookEdition;
    }

    public String getArticleSection() {
        return articleSection;
    }

    public void setArticleSection(String articleSection) {
        this.articleSection = articleSection;
    }

    public String getCodeRepository() {
        return codeRepository;
    }

    public void setCodeRepository(String codeRepository) {
        this.codeRepository = codeRepository;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String getRuntimePlatform() {
        return runtimePlatform;
    }

    public void setRuntimePlatform(String runtimePlatform) {
        this.runtimePlatform = runtimePlatform;
    }

    public String getByArtist() {
        return byArtist;
    }

    public void setByArtist(String byArtist) {
        this.byArtist = byArtist;
    }

    public String getInAlbum() {
        return inAlbum;
    }

    public void setInAlbum(String inAlbum) {
        this.inAlbum = inAlbum;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public SchemaOrgPerson getActor() {
        return actor;
    }

    public void setActor(SchemaOrgPerson actor) {
        this.actor = actor;
    }

    public SchemaOrgPerson getDirector() {
        return director;
    }

    public void setDirector(SchemaOrgPerson director) {
        this.director = director;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReceivedCitation that = (ReceivedCitation) o;

        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (about != null ? !about.equals(that.about) : that.about != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
        if (fileFormat != null ? !fileFormat.equals(that.fileFormat) : that.fileFormat != null) return false;
        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (numberOfPages != null ? !numberOfPages.equals(that.numberOfPages) : that.numberOfPages != null)
            return false;
        if (bookEdition != null ? !bookEdition.equals(that.bookEdition) : that.bookEdition != null) return false;
        if (articleSection != null ? !articleSection.equals(that.articleSection) : that.articleSection != null)
            return false;
        if (codeRepository != null ? !codeRepository.equals(that.codeRepository) : that.codeRepository != null)
            return false;
        if (programmingLanguage != null ? !programmingLanguage.equals(that.programmingLanguage) : that.programmingLanguage != null)
            return false;
        if (runtimePlatform != null ? !runtimePlatform.equals(that.runtimePlatform) : that.runtimePlatform != null)
            return false;
        if (byArtist != null ? !byArtist.equals(that.byArtist) : that.byArtist != null) return false;
        if (inAlbum != null ? !inAlbum.equals(that.inAlbum) : that.inAlbum != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (actor != null ? !actor.equals(that.actor) : that.actor != null) return false;
        if (director != null ? !director.equals(that.director) : that.director != null) return false;
        return caption != null ? caption.equals(that.caption) : that.caption == null;
    }
}
