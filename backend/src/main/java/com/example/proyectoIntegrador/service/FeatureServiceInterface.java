package com.example.proyectoIntegrador.service;
import com.example.proyectoIntegrador.model.Feature;
import com.example.proyectoIntegrador.model.dto.FeatureDTO;

import java.util.List;
import java.util.Optional;

public interface FeatureServiceInterface {

    Optional<FeatureDTO> findById(Long id);
    List<FeatureDTO> list();
    FeatureDTO save(Feature feature);

}
