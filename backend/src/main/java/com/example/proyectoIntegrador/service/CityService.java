package com.example.proyectoIntegrador.service;
import com.example.proyectoIntegrador.exception.RecordNotFoundException;
import com.example.proyectoIntegrador.model.City;
import com.example.proyectoIntegrador.repository.RepositoryCity;
import com.example.proyectoIntegrador.repository.RepositoryCountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService implements CityServiceInterface{
    @Autowired
    RepositoryCity repository;

    @Autowired
    RepositoryCountry countryRepository;


    @Override
    public City findCityByName(String name) {
        return repository.findCityByName(name);
    }

    @Override
    public Optional<City> delete(Long id) {
        Optional<City> searchedCity = repository.findById(id);
        if (searchedCity.isPresent()){
            repository.deleteById(id);
            return searchedCity;
        }else{
            throw new RecordNotFoundException("City con id "+id.toString()+" no existe !");
        }
    }

    @Override
    public Optional<City> findById(Long id) {
        Optional<City> searchedCity = repository.findById(id);
        if (searchedCity.isPresent()){
            return searchedCity;
        }else{
            throw new RecordNotFoundException("City con id "+id.toString()+" no existe !");
        }
    }

    @Override
    public List<City> list() {
        List<City> listCities = repository.findAll();
        return listCities;
    }


    @Override
    public City save(City city) {
        city.setCountry(countryRepository.findById(city.getCountry().getId()).get());
        city.getCountry().getCities().add(city);
        return repository.save(city);
    }
}
