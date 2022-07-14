package com.example.proyectoIntegrador.repository;

import com.example.proyectoIntegrador.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositoryCity extends JpaRepository<City,Long> {

    City findCityByName(String name);
}
