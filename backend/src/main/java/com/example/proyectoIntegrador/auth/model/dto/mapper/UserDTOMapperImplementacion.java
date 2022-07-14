package com.example.proyectoIntegrador.auth.model.dto.mapper;

import com.example.proyectoIntegrador.auth.model.User;
import com.example.proyectoIntegrador.auth.model.dto.UserDTO;
import com.example.proyectoIntegrador.model.Favorite;
import com.example.proyectoIntegrador.model.dto.FavoriteDTO;
import com.example.proyectoIntegrador.model.dto.mapper.FavoriteDTOMapperImplentation;

import java.util.HashSet;
import java.util.Set;

public class UserDTOMapperImplementacion implements UserDTOMapper{

    @Override
    public UserDTO userToUserDTO(User user) {

        FavoriteDTOMapperImplentation favoriteDTOMapperImplentation = new FavoriteDTOMapperImplentation();
        Set<FavoriteDTO> favoriteDTOSet = new HashSet<>();

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setCity(user.getCity());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoleName(user.getRole().getName().toString());

        for ( Favorite f : user.getFavorites()) {
            favoriteDTOSet.add(favoriteDTOMapperImplentation.favoriteToFavoriteDTO(f));
        }
        userDTO.setFavoriteDTOSet(favoriteDTOSet);

        return userDTO;
    }
}
