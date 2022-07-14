package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.model.City;

import java.util.List;
import java.util.Optional;

public interface CityServiceInterface {

    City findCityByName(String name);
    Optional<City> delete(Long id);
    Optional<City> findById(Long id);
    List<City> list();
    City save(City city);

}
