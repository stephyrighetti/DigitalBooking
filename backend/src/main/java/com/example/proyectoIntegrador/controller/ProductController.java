package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.model.Product;
import com.example.proyectoIntegrador.model.dto.ProductDTO;
import com.example.proyectoIntegrador.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/public/products")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product){
        return ResponseEntity.ok(service.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id).get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id).get());
    }

    @GetMapping("/category-filter/{id}")
    public ResponseEntity<List<ProductDTO>> findProductByCategoryId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findProductByCategoryId(id));
    }

    @GetMapping("/city-filter/{id}")
    public ResponseEntity<List<ProductDTO>> findProductByCityId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findProductByCityId(id));
    }

    @GetMapping("filter/{id}/{startDate}/{endDate}")
    public ResponseEntity<List<ProductDTO>> filterByDateCity(@PathVariable Integer id, @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate ) {
        return ResponseEntity.ok(service.filterByDatesCity(id, startDate, endDate));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> listProduct(){
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/random/{cant}")
    public ResponseEntity<List<ProductDTO>> listRandom(@PathVariable Integer cant) {
        return ResponseEntity.ok(service.listRandom(cant));
    }

    @GetMapping("quantity/{id}")
    public ResponseEntity<Integer> countProductByCategoryId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.countProductByCategoryId(id));
    }


    @GetMapping("favorite-products/{id}")
    public ResponseEntity<List<ProductDTO>> listProductsFavoriteByUser(@PathVariable Long id) {
        return ResponseEntity.ok(service.listProductsFavoriteByUser(id));
    }

    @PostMapping("save-frontend")
    public ResponseEntity<Product> saveProductFrontend (@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(service.saveFrontend(productDTO));
    }

}
