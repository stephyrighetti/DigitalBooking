package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.model.DateNotAvailable;
import com.example.proyectoIntegrador.model.dto.DateNotAvailableDTO;
import com.example.proyectoIntegrador.service.DateNotAvailableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/dates-not-available")
public class DateNotAvailableController {

    @Autowired
    DateNotAvailableService service;

    @PostMapping
    public ResponseEntity<DateNotAvailableDTO> addDateNotAv(@RequestBody DateNotAvailable date){
        return ResponseEntity.ok(service.save(date));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DateNotAvailableDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id).get());
    }

    @GetMapping
    public ResponseEntity<List<DateNotAvailableDTO>> listNotAvailable(){
        return ResponseEntity.ok(service.list());

    }

}
