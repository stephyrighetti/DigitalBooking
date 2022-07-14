package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.auth.repository.RepositoryUser;
import com.example.proyectoIntegrador.model.Score;
import com.example.proyectoIntegrador.repository.RepositoryProduct;
import com.example.proyectoIntegrador.repository.RepositoryScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService implements ScoreServiceInterface{

    @Autowired
    RepositoryScore repository;

    @Autowired
    RepositoryProduct repositoryProduct;

    @Autowired
    RepositoryUser repositoryUser;


    @Override
    public Double averageScoreByProduct(Long id) {
        return repository.averageScoreByProduct(id);
    }

    @Override
    public Score save(Score score) {
        repositoryProduct.findById(score.getProduct().getId()).get().getScores().add(score);
        repositoryUser.findById(score.getUser().getId()).get().getScores().add(score);
        Score newScore = repository.save(score);
        repositoryProduct.findById(score.getProduct().getId()).get().setAverage(repository.averageScoreByProduct(score.getProduct().getId()));
        repositoryProduct.save(repositoryProduct.findById(score.getProduct().getId()).get());
        return newScore;
    }

    @Override
    public List<Score> list() {
        return repository.findAll();
    }
}
