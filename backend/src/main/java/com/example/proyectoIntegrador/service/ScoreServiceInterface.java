package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.model.Score;

import java.util.List;

public interface ScoreServiceInterface {

    Double averageScoreByProduct(Long id);
    Score save(Score score);
    List<Score> list();

}
