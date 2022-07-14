package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.model.Image;
import com.example.proyectoIntegrador.model.dto.ImageDTO;

import java.util.List;
import java.util.Optional;

public interface ImageServiceInterface {

    Optional<ImageDTO> findById(Long id);
    List<ImageDTO> list();
    ImageDTO save(Image image);
}
