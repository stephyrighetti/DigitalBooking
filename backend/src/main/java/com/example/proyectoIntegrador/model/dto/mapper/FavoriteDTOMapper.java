package com.example.proyectoIntegrador.model.dto.mapper;

import com.example.proyectoIntegrador.model.Favorite;
import com.example.proyectoIntegrador.model.dto.FavoriteDTO;

public interface FavoriteDTOMapper {

    FavoriteDTO favoriteToFavoriteDTO(Favorite favorite);
}
