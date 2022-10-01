package com.example.demo.api.service;

import com.example.demo.domain.model.Movie;
import com.example.demo.domain.repository.MovieRepository;
import com.example.demo.msg.MovieMessage;
import com.example.demo.msg.RabbitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private RabbitService rabbitService;

    @Autowired
    private ObjectMapper mapper;

    public List<Movie> getMoviesByGenre(String genre){
        return repository.findAllByGenre(genre);
    }

    public Movie saveMovie(Movie movie){
        Movie save = repository.save(movie);
        try {
            rabbitService.send(new MovieMessage(mapper.writeValueAsString(movie)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return save;
    }

}
