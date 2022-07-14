package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.model.City;
import com.example.proyectoIntegrador.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/cities")
public class CityController {

    @Autowired
    CityService service;

    @PostMapping
    public ResponseEntity<City> addCity(@RequestBody City city){
        return ResponseEntity.ok(service.save(city));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id).get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id).get());
    }

    @GetMapping
    public ResponseEntity<List<City>> listCity(){
        return ResponseEntity.ok(service.list());
    }

}
