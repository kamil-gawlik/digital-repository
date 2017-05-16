package com.digitalrepository.dto;

import com.digitalrepository.domain.SchemaOrgHeader;
import com.digitalrepository.domain.SchemaOrgPerson;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Adas on 2017-05-16.
 */
public class RecordDTO {
    private String id;
    private String name;
    private String about;
    private String author;
    private SchemaOrgPerson creator = null;
    private LocalDateTime dateCreated;
    private List<String> citations;

    public RecordDTO(SchemaOrgHeader header) {
        this.id = header.getId();
        this.name = header.getName();
        this.about = header.getAbout();
        this.author = header.getAuthor();
        this.creator = header.getCreator();
        this.dateCreated = header.getDateCreated();
        this.citations = header.getCitations();
    }

    public String getId() {
        return id;
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
}
