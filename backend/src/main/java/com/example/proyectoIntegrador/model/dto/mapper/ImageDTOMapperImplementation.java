package com.example.proyectoIntegrador.model.dto.mapper;

import com.example.proyectoIntegrador.model.Image;
import com.example.proyectoIntegrador.model.dto.ImageDTO;


public class ImageDTOMapperImplementation implements ImageDTOMapper {

    @Override
    public ImageDTO imageToImageDTO(Image image) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setTitle(image.getTitle());
        imageDTO.setUrl(image.getUrl());
        return imageDTO;
    }


}
