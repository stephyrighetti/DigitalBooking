package com.example.proyectoIntegrador.model.dto.mapper;

import com.example.proyectoIntegrador.model.Feature;
import com.example.proyectoIntegrador.model.dto.FeatureDTO;


public interface FeatureDTOMapper {

    FeatureDTO featureToFeatureDTO(Feature feature);


}
