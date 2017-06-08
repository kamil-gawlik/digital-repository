package com.digitalrepository.web.rest;

import com.digitalrepository.domain.SchemaOrgHeader;
import com.digitalrepository.dto.RecordDTO;
import com.digitalrepository.repository.SchemaOrgHeaderRepository;
import com.digitalrepository.service.ZipService;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Records Endpoint
 * Created by Adas on 2017-05-16.
 */
@RestController
@RequestMapping("/api/records")
public class RecordController {

    @Autowired
    private SchemaOrgHeaderRepository schemaOrgHeaderRepository;
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private ZipService zipService;

    /**
     * Returns a single record specified by record-id
     */
    @RequestMapping(method = RequestMethod.GET)
    public RecordDTO getRecord(@RequestParam("record-id") String recordId){
        SchemaOrgHeader header = schemaOrgHeaderRepository.findById(recordId);
        List<GridFSDBFile> files = gridFsTemplate.find(new Query(Criteria.where("metadata.recordId").is(recordId)));
        return new RecordDTO(header, files);
    }


    /**
     *  Returns all records attachments as ZIP file
     */
    @RequestMapping(value = "/zip", produces = "application/zip")
    public void getZip(@RequestParam("record-id") String recordId, HttpServletResponse response) {
        List<GridFSDBFile> files = gridFsTemplate.find(new Query(Criteria.where("metadata.recordId").is(recordId)));

        if (files.isEmpty()) {
            return;
        }

        String recordName = extractRecordName(files);
        prepareResponse(response, recordName);

        try {
            zipService.makeAndForwardZip(files, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void prepareResponse(HttpServletResponse response, String recordName) {
        response.addHeader("Content-Disposition", "attachment; filename=\""+ recordName +"\"");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private String extractRecordName(List<GridFSDBFile> files) {
        return files.get(0).getMetaData().get("recordName") + ".zip";
    }

}
