package com.example.demo.domain.data;

import com.example.demo.domain.model.Movie;
import com.example.demo.domain.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final MovieRepository repository;

    public DataLoader(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        repository.save( new Movie(1L,"Lion", "Drama", "#href"));
        repository.save(new Movie(2L, "The Return of the King", "Fantasy", "#href"));
        repository.save(new Movie(3L, "Terminator", "Action", "#href"));
        repository.save(new Movie(4L, "The Two Towers", "Fantasy", "#href"));
    }
}
