package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.model.Policy;
import com.example.proyectoIntegrador.model.dto.PolicyDTO;
import com.example.proyectoIntegrador.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/policies")
public class PolicyController {
    @Autowired
    PolicyService service;

    @PostMapping
    public ResponseEntity<PolicyDTO> addPolicy(@RequestBody Policy policy){
        return ResponseEntity.ok(service.save(policy));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Policy> deletePolicy(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id).get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicyDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id).get());
    }

    @GetMapping
    public ResponseEntity<List<PolicyDTO>> listPolicy(){
        return ResponseEntity.ok(service.list());
    }

}
