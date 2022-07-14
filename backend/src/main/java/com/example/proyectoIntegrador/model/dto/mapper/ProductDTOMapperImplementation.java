package com.example.proyectoIntegrador.model.dto.mapper;

import com.example.proyectoIntegrador.model.*;
import com.example.proyectoIntegrador.model.dto.*;

import java.util.HashSet;
import java.util.Set;

public class ProductDTOMapperImplementation implements ProductDTOMapper {

    @Override
    public ProductDTO productToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();

        PolicyDTOMapperImplementation policyDTOMapperImplementation = new PolicyDTOMapperImplementation();
        Set<PolicyDTO> policiesDTO = new HashSet<>();

        FeatureDTOMapperImplementation featureDTOMapperImplementation = new FeatureDTOMapperImplementation();
        Set<FeatureDTO> featuresDTO = new HashSet<>();

        ImageDTOMapperImplementation imageDTOMapperImplementation = new ImageDTOMapperImplementation();
        Set<ImageDTO> imagesDTO = new HashSet<>();

        DateNotAvailableDTOMapperImplementation dateNotAvailableDTOMapperImplementation = new DateNotAvailableDTOMapperImplementation();
        Set<DateNotAvailableDTO> notAvailableDatesDTO = new HashSet<>();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setShortDescription(product.getShortDescription());
        productDTO.setLongDescription(product.getLongDescription());
        productDTO.setAddress(product.getAddress());
        productDTO.setAddressAccommodation(product.getAddressAccommodation());
        productDTO.setAverage(product.getAverage());
        productDTO.setCity(product.getCity().getName());
        productDTO.setCountry(product.getCity().getCountry().getName());
        productDTO.setCategory(product.getCategory().getTitle());
        productDTO.setMainPicture(product.getMainPicture());
        productDTO.setStars(product.getStars());
        productDTO.setLongitude(product.getLongitude());
        productDTO.setLatitude(product.getLatitude());


        for ( Policy p : product.getPolicies()) {
            policiesDTO.add(policyDTOMapperImplementation.policyToPolicyDTO(p));
        }
        productDTO.setPolicies(policiesDTO);

        for ( Image i : product.getImages()) {
            imagesDTO.add(imageDTOMapperImplementation.imageToImageDTO(i));
        }
        productDTO.setImages(imagesDTO);

        for ( Feature f : product.getFeatures()) {
            featuresDTO.add(featureDTOMapperImplementation.featureToFeatureDTO(f));
        }
        productDTO.setFeatures(featuresDTO);

        for ( DateNotAvailable date : product.getDatesNotAvailable()) {
            notAvailableDatesDTO.add(dateNotAvailableDTOMapperImplementation.dateNotAvailableToDTO(date));
        }
        productDTO.setDatesNotAvailable(notAvailableDatesDTO);

        return productDTO;
    }
}
