package com.example.demo.clients;

import com.example.demo.domain.Serie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("serie-service")
public interface SerieClient {

    @GetMapping("/series/{genre}")
    public List<Serie> get(@PathVariable String genre );

    @PostMapping("/series")
    public Serie save(@RequestBody Serie serie);

}
