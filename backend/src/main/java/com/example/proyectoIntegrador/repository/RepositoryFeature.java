package com.example.proyectoIntegrador.repository;

import com.example.proyectoIntegrador.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryFeature extends JpaRepository<Feature,Long> {
}
