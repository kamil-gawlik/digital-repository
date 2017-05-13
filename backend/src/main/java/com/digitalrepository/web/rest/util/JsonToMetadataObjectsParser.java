package com.digitalrepository.web.rest.util;

import com.digitalrepository.domain.ReceivedCitation;
import com.digitalrepository.domain.ReceivedRecordHeader;
import com.digitalrepository.domain.SchemaOrgPerson;
import com.digitalrepository.domain.schemaorg.enums.CitationType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Piotrek on 11.05.2017.
 */
public class JsonToMetadataObjectsParser {

    private ReceivedRecordHeader recordHeader;
    private List<ReceivedCitation> citationList;
    private ObjectMapper mapper;

    public JsonToMetadataObjectsParser() {
        this.mapper = new ObjectMapper();
    }

    public ReceivedRecordHeader getReceivedRecordHeader() {
        return recordHeader;
    }

    public void setRecordHeader(String recordHeader) throws Exception {

        JsonNode node;

        try {
            node = mapper.readTree(recordHeader);
        } catch (IOException e) {
            throw new Exception("JSON Parser: Error reading the record header tree: " + e.toString());
        }

        String name = mapper.convertValue(node.get("name"),String.class);
        String about = mapper.convertValue(node.get("about"),String.class);
        String author = mapper.convertValue(node.get("author"),String.class);
        String description = mapper.convertValue(node.get("description"),String.class);
        SchemaOrgPerson creator = mapper.convertValue(node.get("creator"), SchemaOrgPerson.class);

        this.recordHeader = new ReceivedRecordHeader(name,about,author,description,creator);
    }

    public List<ReceivedCitation> getCitationList() {
        return citationList;
    }

    public void setCitationList(String citationListJsonString) throws Exception {
        this.citationList = new LinkedList<>();
        JsonNode rootNode;

        try {
            rootNode = mapper.readTree(citationListJsonString);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

//        citationList = new LinkedList<>();
//        SchemaOrgTagsSelector selector = new SchemaOrgTagsSelector(CitationType.CreativeWork);
//        List<String> creativeWorkTags = selector.getTags();

        for (JsonNode node : rootNode) {

            String citationTypeString = mapper.convertValue(node.get("type"),String.class);
            CitationType type = null;
            for (CitationType citationType : CitationType.values()) {
                if (citationType.toString().equals(citationTypeString)) {
                    type = citationType;
                    break;
                }
            }
            if (type == null) {
                throw new Exception("JSON Parser: Unsupported media type \"" + citationTypeString + "\" in the metadata of the citation!");
            }

            String name = mapper.convertValue(node.get("name"),String.class);
            String about = mapper.convertValue(node.get("about"),String.class);
            String author = mapper.convertValue(node.get("author"),String.class);
            SchemaOrgPerson creator = mapper.convertValue(node.get("creator"),SchemaOrgPerson.class);
            String fileFormat = mapper.convertValue(node.get("fileFormat"),String.class);
            String isbn = mapper.convertValue(node.get("isbn"),String.class);
            String numberOfPages = mapper.convertValue(node.get("numberOfPages"),String.class);
            String bookEdition = mapper.convertValue(node.get("bookEdition"),String.class);
            String articleSection = mapper.convertValue(node.get("articleSection"),String.class);
            String codeRepository = mapper.convertValue(node.get("codeRepository"),String.class);
            String programmingLanguage = mapper.convertValue(node.get("programmingLanguage"),String.class);
            String runtimePlatform = mapper.convertValue(node.get("runtimePlatform"),String.class);
            String byArtist = mapper.convertValue(node.get("byArtist"),String.class);
            String inAlbum = mapper.convertValue(node.get("inAlbum"),String.class);
            String duration = mapper.convertValue(node.get("duration"),String.class);
            SchemaOrgPerson actor = mapper.convertValue(node.get("actor"),SchemaOrgPerson.class);
            SchemaOrgPerson director = mapper.convertValue(node.get("director"),SchemaOrgPerson.class);
            String caption = mapper.convertValue(node.get("caption"),String.class);

            this.citationList.add(new ReceivedCitation(type,name,about,author,creator,fileFormat,
                isbn, numberOfPages,bookEdition,articleSection,codeRepository,programmingLanguage,
                runtimePlatform,byArtist,inAlbum,duration,actor,director,caption));
        }
    }
}
