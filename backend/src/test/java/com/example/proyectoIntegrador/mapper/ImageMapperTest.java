package com.example.proyectoIntegrador.mapper;


import com.example.proyectoIntegrador.model.*;
import com.example.proyectoIntegrador.model.dto.*;
import com.example.proyectoIntegrador.model.dto.mapper.FeatureDTOMapperImplementation;
import com.example.proyectoIntegrador.model.dto.mapper.ImageDTOMapperImplementation;
import com.example.proyectoIntegrador.model.dto.mapper.PolicyDTOMapperImplementation;
import com.example.proyectoIntegrador.model.dto.mapper.ProductDTOMapperImplementation;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class ImageMapperTest {

    @Test
    public void mapImageToDTO(){

        Image image = new Image();
        image.setId(1L);
        image.setTitle("title");
        image.setUrl("url");

        ImageDTOMapperImplementation imageDTOMapperImplementation = new ImageDTOMapperImplementation();
        ImageDTO imageDTO = imageDTOMapperImplementation.imageToImageDTO(image);


        Assert.assertNotNull(imageDTO);
        Assert.assertEquals(image.getTitle(),imageDTO.getTitle());
        Assert.assertEquals(imageDTO.getUrl(),image.getUrl());

    }

    @Test
    public void mapFeatureToDTO(){
        Feature feature = new Feature();
        feature.setId(1L);
        feature.setName("name");

        FeatureDTOMapperImplementation featureDTOMapperImplementation = new FeatureDTOMapperImplementation();
        FeatureDTO featureDTO = featureDTOMapperImplementation.featureToFeatureDTO(feature);

        Assert.assertNotNull(featureDTO);
        Assert.assertEquals(feature.getName(),featureDTO.getName());

    }

    @Test
    public void mapPolicyToDTO(){
        Policy policy = new Policy();
        policy.setId(1L);
        policy.setDescription("description");
        policy.setPolicyType(new PolicyType(1L,"hola"));

        PolicyDTOMapperImplementation policyDTOMapperImplementation = new PolicyDTOMapperImplementation();

        PolicyDTO policyDTO = policyDTOMapperImplementation.policyToPolicyDTO(policy);


        Assert.assertNotNull(policyDTO);
        Assert.assertEquals(policy.getDescription(),policyDTO.getDescription());
        Assert.assertEquals(policy.getPolicyType().getId(),policyDTO.getIdPolicyTpe());

    }

    @Test
    public void mapProductToDTO(){
        Product product = new Product();
        product.setId(1L);
        product.setName("name");
        product.setShortDescription("short");
        product.setLongDescription("long");
        product.setAddress("address");
        product.setAddressAccommodation("addressAccommodation");
        product.setAverage(1.0);
        product.setMainPicture("url");
       // Country country = new Country(1L, "pais");
        //product.setCity(new City(1L,"name", country));
        product.setCategory(new Category(1,"title"));
        product.setMainPicture("main picture");


        Set<Policy> policies = new HashSet<>();
        Policy policy = new Policy();
        policy.setId(1L);
        policy.setDescription("description");
        policy.setPolicyType(new PolicyType(1L,"hola"));
        Policy policy2 = new Policy();
        policy2.setId(2L);
        policy2.setDescription("description2");
        policy2.setPolicyType(new PolicyType(1L,"hola1"));
        policies.add(policy);
        policies.add(policy2);
        product.setPolicies(policies);

        Set<Feature> features= new HashSet<>();
        Feature feature = new Feature();
        feature.setId(1L);
        feature.setName("name");
        Feature feature2 = new Feature();
        feature2.setId(2L);
        feature2.setName("name2");
        features.add(feature);
        features.add(feature2);
        product.setFeatures(features);

        Set<Image> images = new HashSet<>();
        Image image = new Image();
        image.setId(1L);
        image.setTitle("title");
        image.setUrl("url");
        Image image2 = new Image();
        image2.setId(2L);
        image2.setTitle("title2");
        image2.setUrl("url2");
        images.add(image);
        images.add(image2);
        product.setImages(images);


        ProductDTOMapperImplementation productDTOMapperImplementation = new ProductDTOMapperImplementation();
        ProductDTO productDTO = productDTOMapperImplementation.productToProductDTO(product);


        Assert.assertNotNull(productDTO);
        Assert.assertEquals(product.getName(),productDTO.getName());
        Assert.assertEquals(productDTO.getAddress(),product.getAddress());
        Assert.assertEquals(2,product.getImages().size());
        Assert.assertEquals(2,product.getFeatures().size());

    }

}
