package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.RecordNotFoundException;
import com.example.proyectoIntegrador.model.Category;
import com.example.proyectoIntegrador.repository.RepositoryCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryServiceInterface{
    @Autowired
    RepositoryCategory repository;


    @Override
    public Category findCategoryByTitle(String title) {
        return repository.findCategoryByTitle(title);
    }

    @Override
    public Optional<Category> delete(Integer id) {
        Optional<Category> searchedCategory = repository.findById(id);

        if(searchedCategory.isPresent()){
            repository.deleteById(id);
            return searchedCategory;
        }else{
            throw new RecordNotFoundException("Categoría con id "+id.toString()+" no existe !");
        }
    }

    @Override
    public Optional<Category> findById(Integer id){

        Optional<Category> category= repository.findById(id);

        if (category.isPresent()){

//            category.get().calculateTotalProducts();
            return category;
        }else{
            throw new RecordNotFoundException("Categoría con id "+id.toString()+" no existe !");
        }
    }

    @Override
    public List<Category> list(){
        List<Category> listCategories = repository.findAll();

//        for (int i = 0; i < listCategories.size(); i++) {
//            listCategories.get(i).calculateTotalProducts();
//        }
        return listCategories;
    }


    @Override
    public Category modify(Category category) {

        if(findById(category.getId()).isPresent()){
            return repository.save(category);
        }
        else {
            throw new RecordNotFoundException("Categoría con id "+category.getId().toString()+" no existe !");
        }
    }

    @Override
    public Category save(Category category){

        return repository.save(category);
    }

//    public List<Product> findProductByCategory(Integer id){
//        List<Product> listProductsByCategory = repository.findProductByCategory(id);
//
//        return listProductsByCategory;
//    }

}

