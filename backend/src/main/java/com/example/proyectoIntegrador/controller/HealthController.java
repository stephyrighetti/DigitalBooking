package com.example.proyectoIntegrador.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HealthController {
    @GetMapping(value = "/index")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok().build();
    }
}