package com.digitalrepository.web.rest;

import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * A simple controller for uploading files.
 * Created by Piotrek on 30.03.2017.
 */

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> handleFileUpload(@RequestBody MultipartFile file, String filename){
        try {
            InputStream inputStream = new BufferedInputStream(file.getInputStream());
            GridFsOperations gridFsOperations = (GridFsOperations)applicationContext.getBean("gridFsTemplate");
            gridFsOperations.store(inputStream,filename);

        }catch (IOException ioE){
            return new ResponseEntity<String>("Uploading failed", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<String>("File uploaded", HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<String> handleFileFetching(@RequestParam("name")String name){
        GridFsOperations gridFsOperations = (GridFsOperations)applicationContext.getBean("gridFsTemplate");
        List<GridFSDBFile> result = gridFsOperations.find(
            new Query().addCriteria(Criteria.where("filename").is(name)));
        String response = "";
        for(GridFSDBFile file : result){
            response += file.getFilename();
        }
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
