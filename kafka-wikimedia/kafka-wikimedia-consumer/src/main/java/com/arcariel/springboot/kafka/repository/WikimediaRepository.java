package com.arcariel.springboot.kafka.repository;

import com.arcariel.springboot.kafka.entity.WikimediaData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikimediaRepository extends MongoRepository<WikimediaData, String> {
}
