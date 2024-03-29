package com.digitalrepository.web.rest;

import com.digitalrepository.domain.*;
import com.digitalrepository.repository.SchemaOrgHeaderRepository;
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

    private final String UPLOAD_FAILED_MESSAGE = "Uploading new record failed:";

    @Autowired
    private SchemaOrgHeaderRepository schemaOrgHeaderRepository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> handleFileUpload(@RequestParam("filesList") List<MultipartFile> filesList,
                                                   @RequestParam("recordHeader") String recordHeader,
                                                   @RequestParam("fileHeaders") String fileHeaders){

        /**
         * Parse received metadata from JSON to received metadata objects
         */
        JsonToMetadataObjectsParser parser = new JsonToMetadataObjectsParser();
        try {
            parser.setRecordHeader(recordHeader);
            parser.setCitationList(fileHeaders);
        } catch (Exception ex) {
            return new ResponseEntity<String>(UPLOAD_FAILED_MESSAGE +" " + ex.toString(), HttpStatus.CONFLICT);
        }


        ReceivedRecordHeader receivedRecordHeader = parser.getReceivedRecordHeader();
        List<CitationMetadata> receivedCitationList = parser.getCitationList();

        /**
         * Create record header and add it to the Database
         */
        SchemaOrgHeader schemaOrgHeader;
        try {
            SchemaOrgHeaderFactory factory = new SchemaOrgHeaderFactory(receivedRecordHeader, receivedCitationList, filesList);
            schemaOrgHeader = factory.getSchemaOrgHeader();
        } catch (Exception e) {
            return new ResponseEntity<String>(UPLOAD_FAILED_MESSAGE +" " + e.toString(), HttpStatus.CONFLICT);
        }

        /**
         * Save the record header
         */
        schemaOrgHeaderRepository.save(schemaOrgHeader);
        try {
            for ( CitationMetadata meta : receivedCitationList)
                meta.addData("recordId", schemaOrgHeader.getId());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            //For each file included in the record
            for (int i=0; i<filesList.size(); i++){
                /**
                 * Save file to the MongoDB
                 */
                gridFsTemplate.store(filesList.get(i).getInputStream(), receivedCitationList.get(i));
            }
        }catch (IOException ioE){
            return new ResponseEntity<String>(UPLOAD_FAILED_MESSAGE + ": " + ioE, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<String>(schemaOrgHeader.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<String> handleFileFetching(@RequestParam("record-name")String recordName){

        SchemaOrgHeader header = schemaOrgHeaderRepository.findByName(recordName).get(0);
        if(header == null)
            return new ResponseEntity<String>("Record " + recordName + " not found!", HttpStatus.CONFLICT);

        return new ResponseEntity<String>(header.toString(), HttpStatus.OK);
    }
}
