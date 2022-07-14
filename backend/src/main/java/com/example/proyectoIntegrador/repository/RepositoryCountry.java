package com.example.proyectoIntegrador.repository;

import com.example.proyectoIntegrador.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryCountry extends JpaRepository<Country,Long> {

    Country findCountryByName(String name);

}
