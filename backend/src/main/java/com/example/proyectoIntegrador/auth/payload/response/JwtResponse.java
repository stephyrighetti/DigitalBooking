package com.example.proyectoIntegrador.auth.payload.response;

import com.example.proyectoIntegrador.model.dto.FavoriteDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private List<String> role;
    private String name;
    private String surname;
    private String city;
    private Set<FavoriteDTO> favorites = new HashSet<>();

    public JwtResponse(String accessToken, Long id, String email, String name, String surname, String city, List<String> role, Set<FavoriteDTO> favorites ) {
        this.token = accessToken;
        this.id = id;
        this.email = email;
        this.name= name;
        this.surname =surname;
        this.city = city;
        this.role = role;
        this.favorites = favorites;
    }


    public List<String> getRole() {
        return role;
    }
}
