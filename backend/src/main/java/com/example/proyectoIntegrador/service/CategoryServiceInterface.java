package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryServiceInterface {

    Category findCategoryByTitle(String title);
    Optional<Category> delete(Integer id);
    Optional<Category> findById(Integer id);
    List<Category> list();
    Category modify(Category category);
    Category save(Category category);

}
