package com.example.demo.domain.repository;

import com.example.demo.domain.model.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SerieRepository extends MongoRepository<Serie, Long> {
    List<Serie> findAllByGenre(String genre);
}
