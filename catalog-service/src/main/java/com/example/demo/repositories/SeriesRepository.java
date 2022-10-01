package com.example.demo.repositories;

import com.example.demo.domain.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends MongoRepository<Serie, Long> {
}
