package com.example.demo.api;

import com.example.demo.clients.MovieClient;
import com.example.demo.clients.SerieClient;
import com.example.demo.domain.Genre;
import com.example.demo.domain.Movie;
import com.example.demo.domain.Serie;
import com.example.demo.repositories.MoviesRepository;
import com.example.demo.repositories.SeriesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CatalogService {

    @Autowired
    private MovieClient movieClient;

    @Autowired
    private MoviesRepository moviesRepository;
    @Autowired
    private SeriesRepository seriesRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SerieClient serieClient;

    @RabbitListener(queues = "movies")
    public void listenMovies(String in) {
        try {
            moviesRepository.save(objectMapper.readValue(in, Movie.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @RabbitListener(queues = "series")
    public void listenSeries(String in) {
        try {
            seriesRepository.save(objectMapper.readValue(in, Serie.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Movie> findMoviesByGenre(String genre) {
        List<Movie> byGenre = movieClient.findByGenre(genre);
        for (Movie movie : byGenre) {
            moviesRepository.save(movie);
        }
        return byGenre;
    }

    @CircuitBreaker(name="series", fallbackMethod = "seriesFallback")
    public List<Serie> findSeriesByGenre(String genre) {
        List<Serie> series = serieClient.get(genre);
        for (Serie serie : series) {
            seriesRepository.save(serie);
        }
        return series;
    }
// si falla devuelve una lista vacia
    public List<Serie> seriesFallback(CallNotPermittedException permittedException){
        return Collections.emptyList();
    }
}
