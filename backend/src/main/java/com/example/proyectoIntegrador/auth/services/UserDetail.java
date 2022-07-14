package com.example.proyectoIntegrador.auth.services;

import com.example.proyectoIntegrador.auth.model.Role;
import com.example.proyectoIntegrador.auth.model.User;
import com.example.proyectoIntegrador.model.Favorite;
import com.example.proyectoIntegrador.model.dto.FavoriteDTO;
import com.example.proyectoIntegrador.model.dto.mapper.FavoriteDTOMapperImplentation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.*;
import java.util.stream.Collectors;

@Setter
@Getter
public class UserDetail implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String email;

    @JsonIgnore
    private String password;

    private String name;
    private String surname;
    private String city;
    private Set<FavoriteDTO> favorites = new HashSet<>();

    private boolean active;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetail(Long id, String email, String password, String name, String surname, String city, Set<FavoriteDTO> favorites, boolean active,
                      Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname=surname;
        this.city=city;
        this.favorites = favorites;
        this.authorities = authorities;
        this.active = active;
    }

    public static UserDetail build(User user) {
        Role myRole = new Role();
        myRole.setName(user.getRole().getName());
        user.setRole(myRole);

        List<Role> roles = new ArrayList<>();
        roles.add(myRole);

        FavoriteDTOMapperImplentation favoriteDTOMapperImplentation = new FavoriteDTOMapperImplentation();
        Set<FavoriteDTO> favoriteDTOSet = new HashSet<>();

        for ( Favorite f : user.getFavorites()) {
            favoriteDTOSet.add(favoriteDTOMapperImplentation.favoriteToFavoriteDTO(f));
        }


        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetail(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getCity(),
                favoriteDTOSet,
                user.getActive(),
                authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

}
