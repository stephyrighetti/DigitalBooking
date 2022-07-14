package com.example.proyectoIntegrador.auth.repository;

import com.example.proyectoIntegrador.auth.model.ERole;
import com.example.proyectoIntegrador.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRole extends JpaRepository<Role, Long> {
    Role findByName(ERole name);
}
