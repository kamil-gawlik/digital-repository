package com.digitalrepository.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Class representing a record header.
 * Headers are stored in a "record-headers" collection.
 * Created by Piotrek on 27.04.2017.
 */
@Document(collection = "record-headers")
public class Header {

    @Id
    private String id;
    private String recordName;
    private String user;
    private String description;
    private List<String> fileLinks;

    public Header(String recordName, String user, String description, List<String> fileLinks) {
        this.recordName = recordName;
        this.user = user;
        this.description = description;
        this.fileLinks = fileLinks;
    }

    public String getId() {
        return id;
    }

    public String getRecordName() {
        return recordName;
    }

    public String getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getFileLinks() {
        return fileLinks;
    }
}
