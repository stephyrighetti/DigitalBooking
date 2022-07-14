package com.example.proyectoIntegrador.controller;
import com.example.proyectoIntegrador.model.Category;
import com.example.proyectoIntegrador.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/categories")
public class CategoryController {
    @Autowired
    CategoryService service;

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        return ResponseEntity.ok(service.save(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Integer id) {
        return ResponseEntity.ok(service.delete(id).get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id).get());
    }

    @GetMapping
    public ResponseEntity<List<Category>> listCategories(){
        return ResponseEntity.ok( service.list());
    }

    @PutMapping
    public ResponseEntity<Category> modifyCategory(@RequestBody Category category){
        return ResponseEntity.ok(service.modify(category));
    }
}
