package com.example.proyectoIntegrador.model.dto.mapper;

import com.example.proyectoIntegrador.model.Favorite;
import com.example.proyectoIntegrador.model.Product;
import com.example.proyectoIntegrador.model.dto.FavoriteDTO;
import com.example.proyectoIntegrador.model.dto.ProductDTO;

public class FavoriteDTOMapperImplentation implements FavoriteDTOMapper{

    @Override
    public FavoriteDTO favoriteToFavoriteDTO(Favorite favorite) {

        ProductDTOMapperImplementation productDTOMapperImplementation = new ProductDTOMapperImplementation();

        FavoriteDTO favoriteDTO = new FavoriteDTO();

        Product product = favorite.getProduct();
        ProductDTO productDTO = productDTOMapperImplementation.productToProductDTO(product);

        favoriteDTO.setId(favorite.getId());
        favoriteDTO.setProduct(productDTO);


        return favoriteDTO;
    }
}
