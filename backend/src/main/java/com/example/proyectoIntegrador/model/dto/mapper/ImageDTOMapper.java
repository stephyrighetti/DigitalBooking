package com.example.proyectoIntegrador.model.dto.mapper;

import com.example.proyectoIntegrador.model.Image;
import com.example.proyectoIntegrador.model.dto.ImageDTO;

public interface ImageDTOMapper {

    ImageDTO imageToImageDTO(Image image);

}
