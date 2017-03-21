package com.digitalrepository.repository;

import com.digitalrepository.domain.Publication;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Adas on 2017-03-20.
 */
public interface PublicationRepository extends MongoRepository<Publication, String> {
    Publication findByAuthor(String author);
}
