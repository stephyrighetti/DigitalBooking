package com.example.proyectoIntegrador.model.dto.mapper;

import com.example.proyectoIntegrador.model.Product;
import com.example.proyectoIntegrador.model.dto.ProductDTO;


public interface ProductDTOMapper {

    ProductDTO productToProductDTO(Product product);

}
