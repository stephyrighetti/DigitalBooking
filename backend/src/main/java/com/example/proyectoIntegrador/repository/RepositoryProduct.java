package com.example.proyectoIntegrador.repository;


import com.example.proyectoIntegrador.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RepositoryProduct extends JpaRepository<Product,Long> {

    List<Product> findProductByCategoryId(Integer id);
    List<Product> findProductByCityId(Long id);
    Integer countProductByCategoryId(Integer id);


    @Query(value = "SELECT * FROM products ORDER BY rand() LIMIT ?1", nativeQuery = true)
    List<Product> productsRandom(Integer cant);

    @Query(value = "SELECT * FROM products prods WHERE prods.city_id= ?1 AND prods.id NOT IN(SELECT dates_not_available.product_id FROM dates_not_available INNER JOIN products ON (dates_not_available.product_id = products.id) WHERE (products.city_id= ?1 AND date_not_available >=CURRENT_DATE AND date_not_available BETWEEN ?2 AND ?3));", nativeQuery = true)
    List<Product> filterDatesCity( Integer id, LocalDate startDate,LocalDate endDate);

}
