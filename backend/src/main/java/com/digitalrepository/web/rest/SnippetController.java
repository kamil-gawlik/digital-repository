package com.digitalrepository.web.rest;


import com.digitalrepository.dto.SnippetDTO;
import com.digitalrepository.repository.SchemaOrgHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Adas on 2017-05-04.
 */
@RestController
@RequestMapping("/api/snippets")
public class SnippetController {

    @Autowired
    private SchemaOrgHeaderRepository schemaOrgHeaderRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<SnippetDTO> get(@RequestParam("record-name")String recordName){
        return schemaOrgHeaderRepository.findByName(recordName)
            .stream()
            .map(SnippetDTO::new)
            .collect(Collectors.toList());
    }

}
