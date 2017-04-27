package com.digitalrepository.repository;

import com.digitalrepository.domain.Header;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoDB repository for saving headers
 * Created by Piotrek on 27.04.2017.
 */
public interface RecordHeaderRepository extends MongoRepository<Header, String> {
    Header findByRecordName(String recordName);
    Header findByUser(String user);
}
