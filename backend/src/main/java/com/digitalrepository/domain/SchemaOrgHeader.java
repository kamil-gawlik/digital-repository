package com.digitalrepository.domain;

import com.mongodb.DBObject;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
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
    private DBObject creator = null;
    private LocalDateTime dateCreated;
    private List<DBObject> citations;

    public SchemaOrgHeader(String name, String about, String author, DBObject creator, List<DBObject> citations) {
        this.name = name;
        this.about = about;
        this.author = author;
        this.creator = creator;
        this.citations = citations;
        this.dateCreated = LocalDateTime.now();
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

    public DBObject getCreator() {
        return creator;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public List<DBObject> getCitations() {
        return citations;
    }
}
