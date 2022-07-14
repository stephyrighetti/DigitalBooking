
package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.RecordNotFoundException;
import com.example.proyectoIntegrador.model.Category;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.validation.*;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {


 //validacion de errores
    private Validator validator;

    final Category categorySave = new Category("Categoria test", "Descripción de categoria test", "url categoria test");
    final Category categorySaveTitleVacio = new Category("", "Descripción de categoria test", "url categoria test");
    final Category categoryList = new Category("Categoria list", "Descripción de categoria list", "imagen de categoria list");
    final Category categoryDelete = new Category("Categoria delete", "Descripción de categoria delete", "imagen de categoria delete");
    final Category categoryUpdate = new Category("Categoria", "Descripción de categoria update", "imagen de categoria update");
    final Category categoryByID = new Category("Categoria por ID", "Descripción de categoria por ID", "imagen de categoria por ID");

    @Autowired
    CategoryService service;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterEach
    void tearDown() {
    }

   @Test
    @DisplayName("Metodo Save: verifica si guarda una categoria con todos sus atributos")
    void save() {
        Category c = service.save(categorySave);
        Category categoryBd = service.findById(c.getId()).get();
        Assertions.assertEquals(c.getId().toString(), categoryBd.getId().toString());
    }


    @Test
    @DisplayName("Metodo Save: No permitir guardar una categoria con title vacio")
    void saveTitleVacio() {
        Set<ConstraintViolation<Category>> violations = validator.validate(categorySaveTitleVacio);
        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Metodo list: verificar que una categoría exista en un list Categorias")
    void list() {
        Category category = service.save(categoryList);
        List<Category> categories = service.list();
        Assertions.assertTrue(categories.stream().anyMatch(o -> category.getId().equals(o.getId())));
    }


    @Test
    @DisplayName("Metodo delete: eliminar una categoria que existe")
    void delete() {
        Category category = service.save(categoryDelete);
        Assertions.assertTrue(service.delete(category.getId()).isPresent());
    }

    @Test
    @DisplayName("Metodo delete: eliminar una categoria con id inexistente debe arrojar una Exception del tipo NotFoundException")
    void deleteIdNoExiste() {
        Throwable throwable =  assertThrows(Throwable.class, () -> {
            service.delete(4000);
        });
        Assertions.assertEquals(RecordNotFoundException.class, throwable.getClass());

    }

    @Test
    @DisplayName("Metodo modify: actualizar el title a una categoria")
    void modify() {
        Category category = service.save(categoryUpdate);
        category.setTitle("Categoria Update");
        service.modify(category);
        Category categoryBD = service.findById(category.getId()).get();
        Assertions.assertEquals(categoryBD.getTitle(), "Categoria Update");
    }

    @Test
    @DisplayName("Metodo findById: Buscar una categoría con id existente")
    void findById() {
        Category category = service.save(categoryByID);
        Category categoryBD = service.findById(category.getId()).get();
        Assertions.assertEquals(categoryBD.getId(), category.getId());
    }

    @Test
    @DisplayName("Metodo findById: Buscar una categoría con id inexistente")
    void findByIdInexistente() {
        Throwable throwable =  assertThrows(Throwable.class, () -> {
            service.findById(5000).get();
        });
        Assertions.assertEquals(RecordNotFoundException.class, throwable.getClass());
    }
}

