package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.model.Favorite;
import com.example.proyectoIntegrador.model.dto.FavoriteDTO;
import com.example.proyectoIntegrador.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/public/favorites")
public class FavoriteController {

    @Autowired
    FavoriteService service;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Favorite>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Favorite> delete (@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @PostMapping
    public ResponseEntity<FavoriteDTO> save(@RequestBody Favorite favorite) {
        return ResponseEntity.ok(service.save(favorite));
    }

}
