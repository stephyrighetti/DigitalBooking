package com.example.proyectoIntegrador.service;
import com.example.proyectoIntegrador.model.Policy;
import com.example.proyectoIntegrador.model.dto.PolicyDTO;

import java.util.List;
import java.util.Optional;

public interface PolicyServiceInterface {

    Optional<Policy> delete(Long id);
    Optional<PolicyDTO> findById(Long id);
    List<PolicyDTO> list();
    PolicyDTO save(Policy policy);

}
