package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.RecordNotFoundException;
import com.example.proyectoIntegrador.model.Image;
import com.example.proyectoIntegrador.model.dto.ImageDTO;
import com.example.proyectoIntegrador.model.dto.mapper.ImageDTOMapperImplementation;
import com.example.proyectoIntegrador.repository.RepositoryImage;
import com.example.proyectoIntegrador.repository.RepositoryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService implements ImageServiceInterface{

    @Autowired
    RepositoryImage repository;

    @Autowired
    RepositoryProduct repositoryProduct;


    @Override
    public Optional<ImageDTO> findById(Long id) {
        Optional<Image> searchedImage = repository.findById(id);

        ImageDTOMapperImplementation imageDTOMapperImplementation = new ImageDTOMapperImplementation();

        ImageDTO imageDTO = imageDTOMapperImplementation.imageToImageDTO(searchedImage.get());
        Optional<ImageDTO> optionalImageDTO = Optional.of(imageDTO);

        if (optionalImageDTO.isPresent()){
            return optionalImageDTO;
        }else{
            throw new RecordNotFoundException("Image con id "+id.toString()+" no existe !");
        }

    }

    @Override
    public List<ImageDTO> list() {

        List<Image> listImages = repository.findAll();
        ImageDTOMapperImplementation imageDTOMapperImplementation = new ImageDTOMapperImplementation();
        List<ImageDTO> imageDTOList = new ArrayList<>();

        for (Image image : listImages) {
            imageDTOList.add(imageDTOMapperImplementation.imageToImageDTO(image));
        }

        return imageDTOList;
    }


    @Override
    public ImageDTO save(Image image) {

        ImageDTOMapperImplementation imageDTOMapperImplementation = new ImageDTOMapperImplementation();

        if(repositoryProduct.findById(image.getProduct().getId()).get().getImages().size() < 6){
            repositoryProduct.findById(image.getProduct().getId()).get().getImages().add(image);
            image.setProduct(repositoryProduct.findById(image.getProduct().getId()).get());
        }

        Image imageSaved = repository.save(image);


        ImageDTO imageDTO = imageDTOMapperImplementation.imageToImageDTO(imageSaved);

        return imageDTO;

    }
}
