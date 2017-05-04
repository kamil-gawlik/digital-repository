package com.digitalrepository.repository;

import com.digitalrepository.domain.SchemaOrgHeader;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Piotrek on 04.05.2017.
 */
public interface SchemaOrgHeaderRepository extends MongoRepository<SchemaOrgHeader, String> {
    SchemaOrgHeader findByName(String name);
}
