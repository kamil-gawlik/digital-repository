package com.digitalrepository.dto;

import com.digitalrepository.domain.Header;

/**
 * Created by Adas on 2017-05-04.
 */
public class SnippetDTO {
    private String id;
    private String recordName;
    private String user;
    private String description;
    private int attachmentsCount;

    public SnippetDTO(Header header) {
        this.id = header.getId();
        this.recordName = header.getRecordName();
        this.user = header.getUser();
        this.description = header.getDescription();
        this.attachmentsCount = header.getFileLinks().size();
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

    public int getAttachmentsCount() {
        return attachmentsCount;
    }
}
