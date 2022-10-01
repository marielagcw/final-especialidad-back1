package com.example.demo.api.controller;

import com.example.demo.api.service.SerieService;
import com.example.demo.domain.model.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SerieController {

    @Autowired
    private SerieService serieService;

    @GetMapping("/series/{genre}")
    public List<Serie> get(@PathVariable String genre ){
        return serieService.getSeriesByGenre(genre);
    }

    @PostMapping("/series")
    public Serie save(@RequestBody Serie serie){
        return serieService.save(serie);
    }

}
