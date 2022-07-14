package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.model.Country;
import com.example.proyectoIntegrador.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/countries")
public class CountryController {
    @Autowired
    CountryService service;

    @PostMapping
    public ResponseEntity<Country> addCountry(@RequestBody Country country){
        return ResponseEntity.ok(service.save(country));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id).get());
    }

    @GetMapping
    public ResponseEntity<List<Country>> listCountry(){
        return ResponseEntity.ok(service.list());
    }

}
