package com.digitalrepository.web.rest;

import com.digitalrepository.domain.SchemaOrgHeader;
import com.digitalrepository.dto.RecordDTO;
import com.digitalrepository.repository.SchemaOrgHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Records Endpoint
 * Created by Adas on 2017-05-16.
 */
@RestController
@RequestMapping("/api/records")
public class RecordController {

    @Autowired
    private SchemaOrgHeaderRepository schemaOrgHeaderRepository;

    /**
     * Returns a single record specified by record-id
     */
    @RequestMapping(method = RequestMethod.GET)
    public RecordDTO getRecord(@RequestParam("record-id") String recordId){
        SchemaOrgHeader header = schemaOrgHeaderRepository.findById(recordId);
        return new RecordDTO(header);
    }

}
