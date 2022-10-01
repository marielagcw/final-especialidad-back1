package com.example.demo.clients;

import com.example.demo.domain.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "movie-service")
public interface MovieClient {
    @GetMapping("/movies/{genre}")
    public List<Movie> findByGenre(@PathVariable String genre);

    @PostMapping("/movie")
    public Movie save(@RequestBody Movie movie);

}
