package com.example.proyectoIntegrador.repository;

import com.example.proyectoIntegrador.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositoryCategory extends JpaRepository<Category,Integer> {

    Category findCategoryByTitle(String title);

}
