package com.example.proyectoIntegrador.service;
import com.example.proyectoIntegrador.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryServiceInterface {

    Country findCountryByName(String name);
    Optional<Country> findById(Long id);
    List<Country> list();
    Country save(Country country);

}
