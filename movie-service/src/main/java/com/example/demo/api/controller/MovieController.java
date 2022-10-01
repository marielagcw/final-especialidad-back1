package com.example.demo.api.controller;

import com.example.demo.api.service.MovieService;
import com.example.demo.domain.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies/{genre}")
    public List<Movie> findByGenre(@PathVariable String genre){
        return movieService.getMoviesByGenre(genre);
    }

    @PostMapping("/movie")
    public Movie save(@RequestBody Movie movie){
        return movieService.saveMovie(movie);
    }

}
