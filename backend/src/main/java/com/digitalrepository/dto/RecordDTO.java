package com.digitalrepository.dto;

import com.digitalrepository.domain.SchemaOrgHeader;
import com.digitalrepository.domain.SchemaOrgPerson;
import com.mongodb.gridfs.GridFSDBFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<String> filesNames;

    public RecordDTO(SchemaOrgHeader header, List<GridFSDBFile> files) {
        this.id = header.getId();
        this.name = header.getName();
        this.about = header.getAbout();
        this.author = header.getAuthor();
        this.creator = header.getCreator();
        this.dateCreated = header.getDateCreated();
        this.citations = header.getCitations();
        this.filesNames = files.stream()
            .map(x -> x.getFilename())
            .collect(Collectors.toList());
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

    public List<String> getFilesNames() {
        return filesNames;
    }
}
