package com.digitalrepository.repository;

import com.digitalrepository.domain.Header;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * MongoDB repository for saving headers
 * Created by Piotrek on 27.04.2017.
 */
public interface RecordHeaderRepository extends MongoRepository<Header, String> {
    List<Header> findByRecordName(String recordName);
    Header findByUser(String user);
}
