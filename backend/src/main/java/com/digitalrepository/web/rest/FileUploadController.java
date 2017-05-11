package com.digitalrepository.web.rest;

import com.digitalrepository.domain.ReceivedCitation;
import com.digitalrepository.domain.ReceivedRecordHeader;
import com.digitalrepository.repository.SchemaOrgHeaderRepository;
import com.digitalrepository.web.rest.util.AbstractMetadataExtractor;
import com.digitalrepository.web.rest.util.ImageMetadataExtractor;
import com.digitalrepository.web.rest.util.JsonToMetadataObjectsParser;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * A simple controller for uploading files.
 * Created by Piotrek on 30.03.2017.
 */

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    @Autowired
    private SchemaOrgHeaderRepository schemaOrgHeaderRepository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("filesList") List<MultipartFile> filesList,
                                                   @RequestParam("recordHeader") String recordHeader,
                                                   @RequestParam("fileHeaders") String fileHeaders){

        /**
         * Parse received metadata from JSON to received metadata objects
         */
        JsonToMetadataObjectsParser parser = new JsonToMetadataObjectsParser();
        parser.setRecordHeader(recordHeader);
        parser.setCitationList(fileHeaders);

        ReceivedRecordHeader receivedRecordHeader = parser.getReceivedRecordHeader();
        List<ReceivedCitation> receivedCitationList = parser.getCitationList();

        if(receivedCitationList == null || receivedRecordHeader == null) {
            return new ResponseEntity<String>("Uploading new record failed:\nJSON parser error.", HttpStatus.CONFLICT);
        }

        /**
         * Create record header and add it to the Database
         */

        //Header header = new Header(recordName, user, description, new ArrayList<>(), new Date());
       // for(MultipartFile file : filesList)
          //  header.getFileLinks().add(file.getOriginalFilename());

        //recordHeaderRepository.save(header);
        //String record_id = header.getId();

        try {
            //For each file included in the record
            for(MultipartFile file : filesList){
                /**
                 * Create metadata for the file
                 */
                DBObject metaData = new BasicDBObject();
               // metaData.put("record-id", record_id);
                metaData.put("filename", file.getOriginalFilename());
               // metaData.put("user", user);
                //metaData.put("description", description);
                metaData.put("content-type", file.getContentType());

                /**
                 * Extract files metadata
                 */
                //For now only works for image files - need to make a switch here that would choose
                //the correct metadata extractor according to the file content type
                AbstractMetadataExtractor amext = new ImageMetadataExtractor(file.getInputStream());

                DBObject extractedMetadata = new BasicDBObject();
                try {
                    extractedMetadata = amext.getMetadata();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                metaData.put("extracted-metadata", extractedMetadata);

                /**
                 * Save file to the MongoDB
                 */
                gridFsTemplate.store(file.getInputStream(), metaData);
            }
        }catch (IOException ioE){
            return new ResponseEntity<String>("Uploading new record failed:\n" + ioE, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<String>("Record " + /*recordName +*/ " uploaded", HttpStatus.OK);
    }

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public ResponseEntity<String> handleFileFetching(@RequestParam("record-name")String recordName){
//
//        String response = "";
//
//        Header header = recordHeaderRepository.findByRecordName(recordName);
//        if(header == null)
//            return null;
//        response += "HEADER\n";
//        response += header.getId() + "\n";
//        response += header.getRecordName()+ "\n";
//        response += header.getUser()+ "\n";
//        response += header.getDescription()+ "\n";
//        response += header.getFileLinks().toString()+ "\n";
//        response += "\n";
//
//        List<GridFSDBFile> result = gridFsTemplate.find(
//            new Query().addCriteria(Criteria.where("metadata.record-id").is(header.getId())));
//
//        for(GridFSDBFile file : result){
//            response += file.getMetaData();
//            response += "\n";
//        }
//        return new ResponseEntity<String>(response, HttpStatus.OK);
//    }
}
