package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.model.Image;
import com.example.proyectoIntegrador.model.dto.ImageDTO;
import com.example.proyectoIntegrador.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/images")
public class ImageController {

    @Autowired
    ImageService service;

    @PostMapping
    public ResponseEntity<ImageDTO> addImage(@RequestBody Image image){
        return ResponseEntity.ok(service.save(image));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id).get());
    }

    @GetMapping
    public ResponseEntity<List<ImageDTO>> listImage(){
        return ResponseEntity.ok(service.list());
    }

}
