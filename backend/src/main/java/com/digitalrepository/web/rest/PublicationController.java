package com.digitalrepository.web.rest;

import com.digitalrepository.domain.Publication;
import com.digitalrepository.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/publications")
public class PublicationController {

    @Autowired
    private PublicationRepository publicationRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Publication> add(@RequestBody Publication publication) {
        publicationRepository.save(publication);
        return new ResponseEntity<>(publication, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Publication> get(@RequestParam(value="author", defaultValue = "Marek") String name){
        Publication publication = publicationRepository.findByAuthor(name);
        return new ResponseEntity<Publication>(publication, HttpStatus.OK);
    }

}
