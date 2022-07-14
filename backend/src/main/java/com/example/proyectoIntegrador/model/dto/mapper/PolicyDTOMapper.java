package com.example.proyectoIntegrador.model.dto.mapper;

import com.example.proyectoIntegrador.model.Policy;
import com.example.proyectoIntegrador.model.dto.PolicyDTO;


public interface PolicyDTOMapper {

    PolicyDTO policyToPolicyDTO(Policy policy);

}
