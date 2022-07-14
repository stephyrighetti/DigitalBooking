package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.model.Score;
import com.example.proyectoIntegrador.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/scores")
public class ScoreController {

    @Autowired
    ScoreService service;

    @GetMapping
    public ResponseEntity<List<Score>> listScore () {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Double> averageScoreByProduct (@PathVariable Long id) {
        return ResponseEntity.ok(service.averageScoreByProduct(id));
    }

    @PostMapping
    public ResponseEntity<Score> save (@RequestBody Score score) {
        return ResponseEntity.ok(service.save(score));
    }

}
