package com.digitalrepository.web.rest;

/**
 * Created by vagrant on 4/6/17.
 */
public class SimpleData {
    private String recordName;
    private String author;
    private String description;


    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
