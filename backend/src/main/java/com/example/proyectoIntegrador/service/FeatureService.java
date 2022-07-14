package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.RecordNotFoundException;
import com.example.proyectoIntegrador.model.Feature;
import com.example.proyectoIntegrador.model.dto.FeatureDTO;
import com.example.proyectoIntegrador.model.dto.mapper.FeatureDTOMapperImplementation;
import com.example.proyectoIntegrador.repository.RepositoryFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FeatureService implements FeatureServiceInterface{
    @Autowired
    RepositoryFeature repository;

    @Override
    public Optional<FeatureDTO> findById(Long id) {
        Optional<Feature> searchedFeature = repository.findById(id);

        FeatureDTOMapperImplementation featureDTOMapperImplementation = new FeatureDTOMapperImplementation();

        FeatureDTO featureDTO = featureDTOMapperImplementation.featureToFeatureDTO(searchedFeature.get());
        Optional<FeatureDTO> optionalFeatureDTO = Optional.of(featureDTO);

        if (optionalFeatureDTO.isPresent()){
            return optionalFeatureDTO;
        }else{
            throw new RecordNotFoundException("Feature con id "+id.toString()+" no existe !");
        }
    }

    @Override
    public List<FeatureDTO> list() {
        List<Feature> listFeatures = repository.findAll();
        FeatureDTOMapperImplementation featureDTOMapperImplementation = new FeatureDTOMapperImplementation();
        List<FeatureDTO> featureDTOList = new ArrayList<>();

        for (Feature listFeature : listFeatures) {
            featureDTOList.add(featureDTOMapperImplementation.featureToFeatureDTO(listFeature));
        }

        return featureDTOList;

    }


    @Override
    public FeatureDTO save(Feature feature) {

        Feature featureSaved = repository.save(feature);

        FeatureDTOMapperImplementation featureDTOMapperImplementation = new FeatureDTOMapperImplementation();
        FeatureDTO featureDTO = featureDTOMapperImplementation.featureToFeatureDTO(featureSaved);

        return featureDTO;
    }
}
