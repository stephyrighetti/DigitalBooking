package com.example.proyectoIntegrador.model.dto.mapper;

import com.example.proyectoIntegrador.model.Policy;
import com.example.proyectoIntegrador.model.dto.PolicyDTO;

public class PolicyDTOMapperImplementation implements PolicyDTOMapper {
    @Override
    public PolicyDTO policyToPolicyDTO(Policy policy) {
        PolicyDTO policyDTO = new PolicyDTO();
        policyDTO.setIdPolicyTpe(policy.getPolicyType().getId());
        policyDTO.setId(policy.getId());
        policyDTO.setDescription(policy.getDescription());

        return policyDTO;
    }
}
