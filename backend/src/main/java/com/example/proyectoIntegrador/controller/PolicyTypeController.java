package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.model.PolicyType;
import com.example.proyectoIntegrador.service.PolicyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/policy-tipes")
public class PolicyTypeController {
    @Autowired
    PolicyTypeService service;

    @PostMapping
    public ResponseEntity<PolicyType> addPolicyType(@RequestBody PolicyType policyType){
        return ResponseEntity.ok(service.save(policyType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicyType>  findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id).get());
    }

    @GetMapping
    public ResponseEntity<List<PolicyType>> listPolicyType(){
        return ResponseEntity.ok(service.list());
    }

}
