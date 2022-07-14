package com.example.proyectoIntegrador.model.dto.mapper;

import com.example.proyectoIntegrador.model.DateNotAvailable;
import com.example.proyectoIntegrador.model.dto.DateNotAvailableDTO;

import java.time.format.DateTimeFormatter;


public class DateNotAvailableDTOMapperImplementation implements DateNotAvailableDTOMapper{
    @Override
    public DateNotAvailableDTO dateNotAvailableToDTO(DateNotAvailable dateNotAvailable) {
        DateNotAvailableDTO dateNotAvailableDTO = new DateNotAvailableDTO();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/M/uuuu");

        dateNotAvailableDTO.setId(dateNotAvailable.getId());
        dateNotAvailableDTO.setDateNotAvailable((formatters.format(dateNotAvailable.getDateNotAvailable())));


        return dateNotAvailableDTO;
    }
}
