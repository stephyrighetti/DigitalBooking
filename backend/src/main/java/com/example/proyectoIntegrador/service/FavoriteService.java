package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.auth.repository.RepositoryUser;
import com.example.proyectoIntegrador.model.Favorite;
import com.example.proyectoIntegrador.model.Product;
import com.example.proyectoIntegrador.model.dto.FavoriteDTO;
import com.example.proyectoIntegrador.model.dto.ProductDTO;
import com.example.proyectoIntegrador.model.dto.mapper.ProductDTOMapperImplementation;
import com.example.proyectoIntegrador.repository.RepositoryFavorite;
import com.example.proyectoIntegrador.repository.RepositoryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService implements FavoriteServiceInterface{

    @Autowired
    RepositoryFavorite repository;

    @Autowired
    RepositoryProduct repositoryProduct;

    @Autowired
    RepositoryUser repositoryUser;

    @Override
    public FavoriteDTO save(Favorite favorite) {

        repositoryProduct.findById(favorite.getProduct().getId()).get().getFavorites().add(favorite);
        repositoryUser.findById(favorite.getUser().getId()).get().getFavorites().add(favorite);

        Favorite favorite1 = new Favorite();
        favorite1 = repository.save(favorite);

        ProductDTOMapperImplementation productDTOMapperImplementation = new ProductDTOMapperImplementation();
        FavoriteDTO favoriteDTO= new FavoriteDTO();

        Product product = repositoryProduct.findById(favorite.getProduct().getId()).get();
        ProductDTO productDTO = productDTOMapperImplementation.productToProductDTO(product);

        favoriteDTO.setId(favorite1.getId());
        favoriteDTO.setProduct(productDTO);

        return favoriteDTO;
    }

    @Override
    public List<Favorite> findFavoriteByUserId(Long id) {
        return repository.findFavoriteByUserId(id);
    }

    @Override
    public List<Favorite> findFavoriteByProductId(Long id) {
        return repository.findFavoriteByProductId(id);
    }

    @Override
    public Favorite delete(Long id) {
        Favorite deleteFavorite = repository.findById(id).get();

        repositoryProduct.findById(deleteFavorite.getProduct().getId()).get().getFavorites().remove(deleteFavorite);
        repositoryUser.findById(deleteFavorite.getUser().getId()).get().getFavorites().remove(deleteFavorite);


        repository.delete(deleteFavorite);

        return deleteFavorite;

    }

    @Override
    public Optional<Favorite> findById(Long id) {
        return repository.findById(id);
    }
}
