package com.example.proyectoIntegrador.repository;

import com.example.proyectoIntegrador.model.DateNotAvailable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryDateNotAvailable extends JpaRepository <DateNotAvailable, Long>{

}
