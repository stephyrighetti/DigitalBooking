package com.example.proyectoIntegrador.repository;


import com.example.proyectoIntegrador.model.PolicyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPolicyType extends JpaRepository<PolicyType,Long> {
}
