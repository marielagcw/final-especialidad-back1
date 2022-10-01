package com.example.demo.api.service;

import com.example.demo.domain.model.Serie;
import com.example.demo.domain.repository.SerieRepository;
import com.example.demo.msg.RabbitService;
import com.example.demo.msg.SerieMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {
    private final SerieRepository serieRepository;

    private final RabbitService rabbitService;
    private final ObjectMapper objectMapper;

    public SerieService(SerieRepository serieRepository, RabbitService rabbitService, ObjectMapper objectMapper) {
        this.serieRepository = serieRepository;
        this.rabbitService = rabbitService;
        this.objectMapper = objectMapper;
    }

    public Serie getSerie(Long id){
        return serieRepository.findById(id).orElseThrow();
    }

    public Serie save(Serie serie){
        Serie save = serieRepository.save(serie);
        try {
            rabbitService.send(new SerieMessage(objectMapper.writeValueAsString(serie)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return save;
    }

    public List<Serie> getSeriesByGenre(String genre) {
        return serieRepository.findAllByGenre(genre);
    }
}
