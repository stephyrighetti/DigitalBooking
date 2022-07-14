package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.model.PolicyType;

import java.util.List;
import java.util.Optional;

public interface PolicyTypeServiceInterface {

    Optional<PolicyType> findById(Long id);
    List<PolicyType> list();
    PolicyType save(PolicyType policyType);

}
