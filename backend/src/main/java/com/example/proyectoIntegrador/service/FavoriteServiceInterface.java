package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.model.Favorite;
import com.example.proyectoIntegrador.model.dto.FavoriteDTO;

import java.util.List;
import java.util.Optional;

public interface FavoriteServiceInterface {

    FavoriteDTO save(Favorite favorite);
    List<Favorite> findFavoriteByUserId(Long id);
    List<Favorite> findFavoriteByProductId(Long id);
    Favorite delete(Long id);
    Optional<Favorite> findById(Long id);

}
