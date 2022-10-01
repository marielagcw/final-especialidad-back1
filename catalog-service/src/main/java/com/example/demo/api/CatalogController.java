package com.example.demo.api;

import com.example.demo.domain.Genre;
import com.example.demo.domain.Movie;
import com.example.demo.domain.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/catalog/genre/{genre}")
    public Genre getByGenre(@PathVariable String genre){
        List<Movie> byGenre = catalogService.findMoviesByGenre(genre);
        List<Serie> series = catalogService.findSeriesByGenre(genre);
        return new Genre(byGenre, series);
    }

}
