package com.example.proyectoIntegrador.repository;

import com.example.proyectoIntegrador.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryImage extends JpaRepository<Image,Long> {
}
