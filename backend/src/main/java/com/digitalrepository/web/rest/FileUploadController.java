package com.digitalrepository.web.rest;

import com.digitalrepository.domain.Header;
import com.digitalrepository.repository.RecordHeaderRepository;
import com.digitalrepository.web.rest.util.AbstractMetadataExtractor;
import com.digitalrepository.web.rest.util.ImageMetadataExtractor;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.ALL;

/**
 * A simple controller for uploading files.
 * Created by Piotrek on 30.03.2017.
 */

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    @Autowired
    private RecordHeaderRepository recordHeaderRepository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> handleFileUpload(@RequestBody(required = false) List<MultipartFile> filesList,
                                                   String recordName, String user, String description){

        /**
         * Create record header and add it to the Database
         */
        Header header = new Header(recordName, user, description, new ArrayList<>());
        for(MultipartFile file : filesList)
            header.getFileLinks().add(file.getOriginalFilename());

        recordHeaderRepository.save(header);
        String record_id = header.getId();

        try {
            //For each file included in the record
            for(MultipartFile file : filesList){
                /**
                 * Create metadata for the file
                 */
                DBObject metaData = new BasicDBObject();
                metaData.put("record-id", record_id);
                metaData.put("filename", file.getOriginalFilename());
                metaData.put("user", user);
                metaData.put("description", description);
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
        return new ResponseEntity<String>("Record " + recordName + " uploaded", HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<String> handleFileFetching(@RequestParam("record-name")String recordName){

        String response = "";

        Header header = recordHeaderRepository.findByRecordName(recordName);
        if(header == null)
            return null;
        response += "HEADER\n";
        response += header.getId() + "\n";
        response += header.getRecordName()+ "\n";
        response += header.getUser()+ "\n";
        response += header.getDescription()+ "\n";
        response += header.getFileLinks().toString()+ "\n";
        response += "\n";

        List<GridFSDBFile> result = gridFsTemplate.find(
            new Query().addCriteria(Criteria.where("metadata.record-id").is(header.getId())));

        for(GridFSDBFile file : result){
            response += file.getMetaData();
            response += "\n";
        }
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
