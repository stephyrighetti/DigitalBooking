package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.RecordNotFoundException;
import com.example.proyectoIntegrador.model.Country;
import com.example.proyectoIntegrador.repository.RepositoryCountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService implements CountryServiceInterface{

    @Autowired
    RepositoryCountry repository;

    @Override
    public Country findCountryByName(String name) {
        return repository.findCountryByName(name);
    }

    @Override
    public Optional<Country> findById(Long id) {
        Optional<Country> searchedCountry = repository.findById(id);
        if (searchedCountry.isPresent()){
            return searchedCountry;
        }else{
            throw new RecordNotFoundException("Country con id "+id.toString()+" no existe !");
        }
    }

    @Override
    public List<Country> list() {
        List<Country> listCountries = repository.findAll();
        return listCountries;
    }


    @Override
    public Country save(Country country) {
        return repository.save(country);
    }
}
