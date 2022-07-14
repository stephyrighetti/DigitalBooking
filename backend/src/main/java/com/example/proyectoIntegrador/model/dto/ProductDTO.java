package com.example.proyectoIntegrador.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {

    Long id;

    String name;

    String shortDescription;

    String longDescription;

    String address;

    String addressAccommodation;

    Double average;

    Integer stars;

    String latitude;

    String longitude;

    String city;

    String country;

    String category;

    String mainPicture;

    Set<PolicyDTO> policies = new HashSet<>();

    Set<ImageDTO> images = new HashSet<>();

    Set<FeatureDTO> features = new HashSet<>();

    Set<DateNotAvailableDTO> datesNotAvailable = new HashSet<>();

}
