package com.digitalrepository.domain;

import com.digitalrepository.domain.schemaorg.enums.CitationType;
import com.digitalrepository.domain.schemaorg.enums.ImageObjectTags;
import com.digitalrepository.web.rest.util.AbstractMetadataExtractor;
import com.digitalrepository.web.rest.util.ImageMetadataExtractor;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Piotrek on 11.05.2017.
 */
public class SchemaOrgHeaderFactory {

    private ReceivedRecordHeader receivedRecordHeader;
    private List<CitationMetadata> citationMetadataList;
    private List<MultipartFile> filesList;
    private SchemaOrgHeader schemaOrgHeader;

    public SchemaOrgHeaderFactory(ReceivedRecordHeader receivedRecordHeader, List<CitationMetadata> citationMetadataList, List<MultipartFile> filesList) {
        this.receivedRecordHeader = receivedRecordHeader;
        this.citationMetadataList = citationMetadataList;
        this.filesList = filesList;
    }

    public SchemaOrgHeader getSchemaOrgHeader() throws Exception {

        SchemaOrgHeader recordHeader = new SchemaOrgHeader();
        recordHeader.setName(receivedRecordHeader.getName());
        recordHeader.setAbout(receivedRecordHeader.getAbout());
        recordHeader.setAuthor(receivedRecordHeader.getAuthor());
        recordHeader.setCreator(receivedRecordHeader.getCreator());

        if( filesList.size() != citationMetadataList.size()) {
            throw new Exception("SchemaOrgHeaderFactory: Files list doesn't match the metadata list.");
        }
        for (int i=0; i<filesList.size(); i++) {
            MultipartFile file = filesList.get(i);
            CitationMetadata metadata = citationMetadataList.get(i);
            metadata.put("recordName",receivedRecordHeader.getName());
            metadata.put("fileName",file.getOriginalFilename());
            metadata.put("fileFormat",file.getContentType());
            if (CitationType.ImageObject.toString().equals(metadata.getType())) {
                AbstractMetadataExtractor amext = new ImageMetadataExtractor(file.getInputStream());
                try {
                    DBObject extractedMetadata = amext.getMetadata();
                    metadata.put(ImageObjectTags.exifData.toString(), extractedMetadata.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            recordHeader.addCitation(metadata.toString());
        }

        return recordHeader;
    }

    public List<CitationMetadata> getCitationMetadataList() {
        return this.citationMetadataList;
    }
}