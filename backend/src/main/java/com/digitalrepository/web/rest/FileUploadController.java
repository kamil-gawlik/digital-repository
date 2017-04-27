package com.digitalrepository.web.rest;

import com.digitalrepository.web.rest.util.AbstractMetadataExtractor;
import com.digitalrepository.web.rest.util.ImageMetadataExtractor;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    private GridFsTemplate gridFsTemplate;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> handleFileUpload(@RequestBody(required = false) List<MultipartFile> filesList,
                                                   String recordName, String author, String description){

        try {

            //For each file included in the record
            for(MultipartFile file : filesList){


                /**
                 * Create metadate for the file
                 */
                DBObject metaData = new BasicDBObject();
                metaData.put("record-name", recordName);
                metaData.put("filename", file.getOriginalFilename());
                metaData.put("author", author);
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

        List<GridFSDBFile> result = gridFsTemplate.find(
            new Query().addCriteria(Criteria.where("metadata.record-name").is(recordName)));
        String response = "";
        for(GridFSDBFile file : result){
            response += file.getMetaData();
            response += "\n";
        }
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
