package com.example.proyectoIntegrador.model.dto.mapper;

import com.example.proyectoIntegrador.model.Feature;
import com.example.proyectoIntegrador.model.dto.FeatureDTO;


public class FeatureDTOMapperImplementation implements FeatureDTOMapper {
    @Override
    public FeatureDTO featureToFeatureDTO(Feature feature) {
        FeatureDTO featureDTO = new FeatureDTO();
        featureDTO.setId(feature.getId());
        featureDTO.setName(feature.getName());

        return featureDTO;
    }
}
