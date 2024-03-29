package com.digitalrepository.repository;

import com.digitalrepository.domain.SchemaOrgHeader;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Piotrek on 04.05.2017.
 */
public interface SchemaOrgHeaderRepository extends MongoRepository<SchemaOrgHeader, String> {
    List<SchemaOrgHeader> findByName(String name);
    SchemaOrgHeader findById(String id);
}
