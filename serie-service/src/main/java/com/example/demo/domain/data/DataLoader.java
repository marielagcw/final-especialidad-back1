package com.example.demo.domain.data;

import com.example.demo.domain.model.Serie;
import com.example.demo.domain.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
public class DataLoader implements ApplicationRunner {

    @Autowired
    private SerieRepository serieRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Serie serie1 = new Serie(1L, "serie1", "genero1");
        Serie serie2 = new Serie(2L, "serie2", "genero2");
        Serie serie3 = new Serie(3L, "serie3", "genero3");
        Serie serie4 = new Serie(4L, "serie4", "genero4");

        serieRepository.save(serie1);
        serieRepository.save(serie2);
        serieRepository.save(serie3);
        serieRepository.save(serie4);

    }
}
