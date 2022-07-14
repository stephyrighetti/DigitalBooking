package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.RecordNotFoundException;
import com.example.proyectoIntegrador.model.Policy;
import com.example.proyectoIntegrador.model.dto.PolicyDTO;
import com.example.proyectoIntegrador.model.dto.mapper.PolicyDTOMapperImplementation;
import com.example.proyectoIntegrador.repository.RepositoryPolicy;
import com.example.proyectoIntegrador.repository.RepositoryPolicyType;
import com.example.proyectoIntegrador.repository.RepositoryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PolicyService implements PolicyServiceInterface{
    @Autowired
    RepositoryPolicy repository;

    @Autowired
    RepositoryPolicyType repositoryPolicyType;

    @Autowired
    RepositoryProduct repositoryProduct;

    @Override
    public Optional<Policy> delete(Long id) {
        Optional<Policy> searchedPolicy = repository.findById(id);
        if (searchedPolicy.isPresent()){
            repository.deleteById(id);
            return searchedPolicy;
        }else {
            throw new RecordNotFoundException("Policy con id "+id.toString()+" no existe !");
        }
    }

    @Override
    public Optional<PolicyDTO> findById(Long id) {
        Optional<Policy> searchedPolicy = repository.findById(id);

        PolicyDTOMapperImplementation policyDTOMapperImplementation = new PolicyDTOMapperImplementation();

        PolicyDTO policyDTO = policyDTOMapperImplementation.policyToPolicyDTO(searchedPolicy.get());

        Optional<PolicyDTO> optionalPolicyDTO = Optional.of(policyDTO);

        if (optionalPolicyDTO.isPresent()){
            return optionalPolicyDTO;
        }else{
            throw new RecordNotFoundException("Policy con id "+id.toString()+" no existe !");
        }
    }

    @Override
    public List<PolicyDTO> list() {

        List<Policy> listPolicies = repository.findAll();

        PolicyDTOMapperImplementation policyDTOMapperImplementation = new PolicyDTOMapperImplementation();
        List<PolicyDTO> policyDTOList = new ArrayList<>();

        for (Policy policy : listPolicies) {
            policyDTOList.add(policyDTOMapperImplementation.policyToPolicyDTO(policy));
        }

        return policyDTOList;
    }

    @Override
    public PolicyDTO save(Policy policy) {

        PolicyDTOMapperImplementation policyDTOMapperImplementation = new PolicyDTOMapperImplementation();

        policy.setPolicyType(repositoryPolicyType.findById(policy.getPolicyType().getId()).get());
        repositoryProduct.findById(policy.getProduct().getId()).get().getPolicies().add(policy);

        Policy policySaved = repository.save(policy);

        PolicyDTO policyDTO = policyDTOMapperImplementation.policyToPolicyDTO(policySaved);

        return policyDTO;

    }
}
