package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.model.Feature;
import com.example.proyectoIntegrador.model.dto.FeatureDTO;
import com.example.proyectoIntegrador.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/features")
public class FeatureController {

    @Autowired
    FeatureService service;

    @PostMapping
    public ResponseEntity<FeatureDTO> addFeature(@RequestBody Feature feature){
        return ResponseEntity.ok(service.save(feature));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeatureDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id).get());
    }

    @GetMapping
    public ResponseEntity<List<FeatureDTO>> listFeature(){
        return ResponseEntity.ok(service.list());

    }


}
