package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class Genre {

    private List<Movie> movies;
    private List<Serie> series;

}
