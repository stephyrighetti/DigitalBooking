package com.example.proyectoIntegrador.auth.model.dto;

import com.example.proyectoIntegrador.model.dto.FavoriteDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {

    Long id;

    String name;

    String surname;

    String email;

    String roleName;

    String city;

    Set<FavoriteDTO> favoriteDTOSet = new HashSet<>();

}

