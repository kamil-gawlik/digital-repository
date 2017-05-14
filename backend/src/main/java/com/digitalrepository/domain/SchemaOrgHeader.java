package com.digitalrepository.domain;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Class representing a record header, compatible with schema.org
 * Headers are stored in a "record-headers" collection.
 * Created by Piotrek on 04.05.2017.
 */
public class SchemaOrgHeader {

    @Id
    private String id;
    private final String context = "http://schema.org";
    private final String type = "CreativeWork";
    private String name;
    private String about;
    private String author;
    private SchemaOrgPerson creator = null;
    private LocalDateTime dateCreated;
    private List<String> citations;

    public SchemaOrgHeader() {
        this.citations = new LinkedList<>();
        this.dateCreated = LocalDateTime.now();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCreator(SchemaOrgPerson creator) {
        this.creator = creator;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void addCitation(String citation) {
        this.citations.add(citation);
    }

    public String getId() {
        return id;
    }

    public String getContext() {
        return context;
    }

    public String getType() {
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public List<String> getCitations() {
        return citations;
    }

    @Override
    public String toString() {
        String result = "{" +
            "\n\"id\":\"" + id + "\"" +
            ",\n\"@context\":\"" + context + "\"" +
            ",\n\"@type\":\"" + type + "\"" +
            ",\n\"name\":\"" + name + "\"" +
            ",\n\"about\":\"" + about + "\"" +
            ",\n\"author\":\"" + author + "\"" +
            ",\n\"creator:" + creator +
            ",\n\"dateCreated\":" + dateCreated + "\"" +
            ",\n\"citations:[\n";
        for (int i=0; i<citations.size(); i++) {
            result += citations.get(i);
            if (i<citations.size()-1)
                result += ",\n";
        }
        result += "\n]\n}";
        return result;
    }
}
