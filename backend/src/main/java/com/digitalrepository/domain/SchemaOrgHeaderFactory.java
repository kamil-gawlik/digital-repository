package com.digitalrepository.domain;

import com.digitalrepository.domain.ReceivedCitation;
import com.digitalrepository.domain.ReceivedRecordHeader;
import com.digitalrepository.domain.SchemaOrgHeader;
import com.digitalrepository.domain.schemaorg.enums.CitationType;
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

    public static SchemaOrgHeader createSchemaOrgHeader(ReceivedRecordHeader receivedRecordHeader, List<ReceivedCitation> receivedCitationList, List<MultipartFile> filesList) throws Exception {

        SchemaOrgHeader recordHeader = new SchemaOrgHeader();
        recordHeader.setName(receivedRecordHeader.getName());
        recordHeader.setAbout(receivedRecordHeader.getAbout());
        recordHeader.setAuthor(receivedRecordHeader.getAuthor());
        recordHeader.setCreator(receivedRecordHeader.getCreator());

        if( filesList.size() != receivedCitationList.size()) {
            throw new Exception("SchemaOrgHeaderFactory: Files list doesn't match the metadata list.");
        }
        for (int i=0; i<filesList.size(); i++) {
            MultipartFile file = filesList.get(i);
            ReceivedCitation metadata = receivedCitationList.get(i);
            metadata.setFileName(file.getOriginalFilename());
            if (CitationType.ImageObject.equals(metadata.getType())) {
                AbstractMetadataExtractor amext = new ImageMetadataExtractor(file.getInputStream());
                DBObject extractedMetadata = new BasicDBObject();
                metadata.setExifData(extractedMetadata.toString());
                try {
                    extractedMetadata = amext.getMetadata();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            recordHeader.addCitation(metadata);
        }

        return recordHeader;
    }
}
