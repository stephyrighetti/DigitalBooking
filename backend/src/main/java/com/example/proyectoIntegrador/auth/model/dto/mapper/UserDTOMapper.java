package com.example.proyectoIntegrador.auth.model.dto.mapper;

import com.example.proyectoIntegrador.auth.model.User;
import com.example.proyectoIntegrador.auth.model.dto.UserDTO;

public interface UserDTOMapper {

    UserDTO userToUserDTO(User user);

}
