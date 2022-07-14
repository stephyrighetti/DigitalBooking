package com.example.proyectoIntegrador.service;
import com.example.proyectoIntegrador.model.Product;
import com.example.proyectoIntegrador.model.dto.ProductDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProductServiceInterface {

    Integer countProductByCategoryId(Integer id);
    Optional<Product> delete(Long id);
    Optional<ProductDTO> findById(Long id);
    List<ProductDTO> findProductByCategoryId(Integer id);
    List<ProductDTO> findProductByCityId(Long id);
    List<ProductDTO> list();
    List<ProductDTO> listRandom(Integer cant);
    List<ProductDTO> filterByDatesCity( Integer id, LocalDate startDate, LocalDate endDate);
    List<ProductDTO> listProductsFavoriteByUser(Long idUser);
    ProductDTO modify(Product product);
    ProductDTO save(Product product);
    Product saveFrontend(ProductDTO productDTO);

}
