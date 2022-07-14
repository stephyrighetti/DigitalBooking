package com.example.proyectoIntegrador.repository;


import com.example.proyectoIntegrador.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPolicy extends JpaRepository<Policy,Long> {
}
