package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.RecordNotFoundException;
import com.example.proyectoIntegrador.model.PolicyType;
import com.example.proyectoIntegrador.repository.RepositoryPolicyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyTypeService implements PolicyTypeServiceInterface{
    @Autowired
    RepositoryPolicyType repository;


    @Override
    public Optional<PolicyType> findById(Long id) {
        Optional<PolicyType> searchedPolityType = repository.findById(id);
        if (searchedPolityType.isPresent()){
            return searchedPolityType;
        }else {
            throw new RecordNotFoundException("Policy con id "+id.toString()+" no existe !");
        }
    }

    @Override
    public List<PolicyType> list() {
        List<PolicyType> listPolityTypes = repository.findAll();
        return listPolityTypes;
    }


    @Override
    public PolicyType save(PolicyType policyType) {
        return repository.save(policyType);
    }
}
