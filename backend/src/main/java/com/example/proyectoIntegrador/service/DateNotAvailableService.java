package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.RecordNotFoundException;
import com.example.proyectoIntegrador.model.DateNotAvailable;
import com.example.proyectoIntegrador.model.dto.DateNotAvailableDTO;
import com.example.proyectoIntegrador.model.dto.mapper.DateNotAvailableDTOMapperImplementation;
import com.example.proyectoIntegrador.repository.RepositoryDateNotAvailable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DateNotAvailableService implements DateNotAvailableServiceInterface{

    @Autowired
    RepositoryDateNotAvailable repository;

    @Override
    public Optional<DateNotAvailableDTO> findById(Long id) {
        Optional<DateNotAvailable> searchedDateNotAvailable = repository.findById(id);

        DateNotAvailableDTOMapperImplementation dateNotAvailableDTOMapperImplementation = new DateNotAvailableDTOMapperImplementation();

        DateNotAvailableDTO dateNotAvailableDTO = dateNotAvailableDTOMapperImplementation.dateNotAvailableToDTO(searchedDateNotAvailable.get());
        Optional<DateNotAvailableDTO> optionalDateNotAvailableDTO = Optional.of(dateNotAvailableDTO);

        if (optionalDateNotAvailableDTO.isPresent()){
            return optionalDateNotAvailableDTO;
        }else{
            throw new RecordNotFoundException("Features con id "+id.toString()+" no existe !");
        }
    }

    @Override
    public Optional<DateNotAvailable> delete(Long id) {
        return Optional.empty();
    }

    @Override
    public List<DateNotAvailableDTO> list() {
        List<DateNotAvailable> listDatesNotAvailable = repository.findAll();
        DateNotAvailableDTOMapperImplementation dateNotAvailableDTOMapperImplementation = new DateNotAvailableDTOMapperImplementation();

        List<DateNotAvailableDTO> dateNotAvailableDTOList = new ArrayList<>();

        for (DateNotAvailable date : listDatesNotAvailable) {
           dateNotAvailableDTOList.add(dateNotAvailableDTOMapperImplementation.dateNotAvailableToDTO(date));
        }

        return dateNotAvailableDTOList;
    }

    @Override
    public DateNotAvailableDTO save(DateNotAvailable dateNotAvailable) {
        DateNotAvailableDTOMapperImplementation dateNotAvailableDTOMapperImplementation = new DateNotAvailableDTOMapperImplementation();

        DateNotAvailable dateNotAvailableSaved = repository.save(dateNotAvailable);

        DateNotAvailableDTO dateNotAvailableDTO = dateNotAvailableDTOMapperImplementation.dateNotAvailableToDTO(dateNotAvailableSaved);

        return dateNotAvailableDTO;
    }
}
