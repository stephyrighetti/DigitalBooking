package com.example.proyectoIntegrador.repository;

import com.example.proyectoIntegrador.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryFavorite extends JpaRepository<Favorite, Long> {

    List<Favorite> findFavoriteByUserId(Long id);
    List<Favorite> findFavoriteByProductId(Long id);
}
