package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.RecordNotFoundException;
import com.example.proyectoIntegrador.model.*;
import com.example.proyectoIntegrador.model.dto.*;
import com.example.proyectoIntegrador.model.dto.mapper.ProductDTOMapperImplementation;
import com.example.proyectoIntegrador.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ProductService implements ProductServiceInterface{

    @Autowired
    RepositoryProduct repository;

    @Autowired
    RepositoryCity repositoryCity;

    @Autowired
    RepositoryCountry repositoryCountry;

    @Autowired
    RepositoryCategory repositoryCategory;

    @Autowired
    RepositoryFeature repositoryFeature;

    @Autowired
    RepositoryPolicy repositoryPolicy;

    @Autowired
    RepositoryPolicyType repositoryPolicyType;

    @Autowired
    RepositoryImage repositoryImage;

    @Autowired
    RepositoryDateNotAvailable repositoryDateNotAvailable;

    @Autowired
    RepositoryFavorite repositoryFavorite;

    @Override
    public Integer countProductByCategoryId(Integer id) {
        return repository.countProductByCategoryId(id);
    }

    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> searchedProduct = repository.findById(id);

        if (searchedProduct.isPresent()){
            repository.deleteById(id);
            repositoryCategory.findById(searchedProduct.get().getCategory().getId()).get().setQuantityProducts(searchedProduct.get().getCategory().getQuantityProducts() - 1);
            return searchedProduct;
        }else{
            throw new RecordNotFoundException("Producto con id "+id.toString()+" no existe !");
        }
    }


    @Override
    public List<ProductDTO> findProductByCategoryId(Integer id){

        List<Product> listProduct = repository.findProductByCategoryId(id);
        ProductDTOMapperImplementation productDTOMapperImplementation = new ProductDTOMapperImplementation();
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product : listProduct) {
            productDTOList.add(productDTOMapperImplementation.productToProductDTO(product));
        }

        return productDTOList;
    }



    @Override
    public List<ProductDTO> findProductByCityId(Long id){

        List<Product> listProduct = repository.findProductByCityId(id);
        ProductDTOMapperImplementation productDTOMapperImplementation = new ProductDTOMapperImplementation();
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product : listProduct) {
            productDTOList.add(productDTOMapperImplementation.productToProductDTO(product));
        }

        return productDTOList;

    }

    @Override

    public Optional<ProductDTO> findById(Long id) {

        Optional<Product> searchedProduct = repository.findById(id);

        ProductDTOMapperImplementation productDTOMapperImplementation = new ProductDTOMapperImplementation();

        ProductDTO productDTO = productDTOMapperImplementation.productToProductDTO(searchedProduct.get());
        Optional<ProductDTO> optionalProductDTO = Optional.of(productDTO);

        if (optionalProductDTO.isPresent()){
            return optionalProductDTO;
        }else{
            throw new RecordNotFoundException("Producto con id "+id.toString()+" no existe !");
        }

    }

    @Override
    public List<ProductDTO> list() {

        List<Product> productsList = repository.findAll();

        ProductDTOMapperImplementation productDTOMapperImplementation = new ProductDTOMapperImplementation();

        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product : productsList) {
            productDTOList.add(productDTOMapperImplementation.productToProductDTO(product));
        }

        return productDTOList;

    }

    @Override
    public List<ProductDTO> listRandom(Integer cant){

        List<Product> productsList = repository.productsRandom(cant);

        ProductDTOMapperImplementation productDTOMapperImplementation = new ProductDTOMapperImplementation();

        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product : productsList) {
            productDTOList.add(productDTOMapperImplementation.productToProductDTO(product));
        }

        return productDTOList;
    }

    @Override
    public List<ProductDTO> filterByDatesCity( Integer id, LocalDate startDate, LocalDate endDate) {
        List<Product> productsList = repository.filterDatesCity(id, startDate, endDate);

        ProductDTOMapperImplementation productDTOMapperImplementation = new ProductDTOMapperImplementation();

        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product : productsList) {
            productDTOList.add(productDTOMapperImplementation.productToProductDTO(product));
        }

        return productDTOList;
    }

    @Override
    public List<ProductDTO> listProductsFavoriteByUser(Long idUser) {
        List<Favorite> userFavorites = repositoryFavorite.findFavoriteByUserId(idUser);
        List<Product> favoriteProductsUser = new ArrayList<>();

        for (Favorite favorite : userFavorites) {
            favoriteProductsUser.add(repository.findById(favorite.getProduct().getId()).get());
        }

        ProductDTOMapperImplementation productDTOMapperImplementation = new ProductDTOMapperImplementation();

        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product : favoriteProductsUser) {
            productDTOList.add(productDTOMapperImplementation.productToProductDTO(product));
        }

        return productDTOList;
    }

    @Override
    public ProductDTO modify(Product product) {
       Optional<Product> productId = repository.findById(product.getId());

        if (productId.isPresent()) {
            repository.save(productId.get());
        }

        ProductDTOMapperImplementation productDTOMapperImplementation = new ProductDTOMapperImplementation();

        ProductDTO productDTO = productDTOMapperImplementation.productToProductDTO(productId.get());

        return productDTO;
    }


    @Override
    public ProductDTO save(Product product) {

        product.setCity(repositoryCity.findById(product.getCity().getId()).get());
        repositoryCity.findById(product.getCity().getId()).get().getProducts().add(product);

        product.setCategory(repositoryCategory.findById(product.getCategory().getId()).get());
        repositoryCategory.findById(product.getCategory().getId()).get().getProducts().add(product);

        Set <Policy> emptyPolicy = new HashSet<>();
        for ( Policy p : product.getPolicies()) {
            emptyPolicy.add(repositoryPolicy.findById(p.getId()).get());
            repositoryPolicy.findById(p.getId()).get().setProduct(product);
        }
        product.setPolicies(emptyPolicy);

        Set <Image> emptyImage = new HashSet<>();
        for ( Image i : product.getImages()) {
            emptyImage.add(repositoryImage.findById(i.getId()).get());
            repositoryImage.findById(i.getId()).get().setProduct(product);
        }
        product.setImages(emptyImage);

        Set <Feature> emptyFeature = new HashSet<>();
        for ( Feature f : product.getFeatures()) {
            emptyFeature.add(repositoryFeature.findById(f.getId()).get());
            repositoryFeature.findById(f.getId()).get().getProducts().add(product);
        }
        product.setFeatures(emptyFeature);

        Set <DateNotAvailable> emptyDate = new HashSet<>();
        for ( DateNotAvailable date : product.getDatesNotAvailable()) {
            emptyDate.add(repositoryDateNotAvailable.findById(date.getId()).get());
            repositoryDateNotAvailable.findById(date.getId()).get().setProduct(product);
        }
        
        product.setDatesNotAvailable(emptyDate);

        Product productId = new Product();
        productId = repository.save(product);

        Category category = repositoryCategory.findById(productId.getCategory().getId()).get();
        category.setQuantityProducts(repository.countProductByCategoryId(product.getCategory().getId()));
        repositoryCategory.save(category);


        ProductDTOMapperImplementation productDTOMapperImplementation = new ProductDTOMapperImplementation();

        ProductDTO productDTO = productDTOMapperImplementation.productToProductDTO(productId);

        return productDTO;

    }

    @Override
    public Product saveFrontend(ProductDTO productDTO) {
        Product savedProduct = new Product();
        City emptyCity = new City();
        Country emptyCountry = new Country();
        Set<DateNotAvailable> listDNA = new HashSet<>();
        Feature emptyFeature = new Feature();
        Image emptyImage = new Image();
        Policy emptyPolicy = new Policy();
        Category emptyCategory = new Category();

        savedProduct.setName(productDTO.getName());
        savedProduct.setShortDescription(productDTO.getShortDescription());
        savedProduct.setLongDescription(productDTO.getLongDescription());
        savedProduct.setAddress(productDTO.getAddress());
        savedProduct.setAddressAccommodation(productDTO.getAddressAccommodation());
        savedProduct.setAverage(productDTO.getAverage());
        emptyCountry = repositoryCountry.findCountryByName(productDTO.getCountry());
        emptyCity = repositoryCity.findCityByName(productDTO.getCity());
        savedProduct.setCity(emptyCity);
        savedProduct.setMainPicture(productDTO.getMainPicture());

        emptyCategory = repositoryCategory.findCategoryByTitle(productDTO.getCategory());
        emptyCategory = repositoryCategory.findCategoryByTitle(productDTO.getCategory());
        savedProduct.setCategory(emptyCategory);

        savedProduct.setStars(productDTO.getStars());
        savedProduct.setLongitude(productDTO.getLongitude());
        savedProduct.setLatitude(productDTO.getLatitude());
        Product productSaved = repository.save(savedProduct);

        for (PolicyDTO policy : productDTO.getPolicies()) {
            Policy newPolicy = new Policy();
            newPolicy.setDescription(policy.getDescription());
            newPolicy.setPolicyType(repositoryPolicyType.findById(policy.getIdPolicyTpe()).get());
            newPolicy.setProduct(productSaved);
            Policy policySaved = repositoryPolicy.save(newPolicy);
            productSaved.getPolicies().add(policySaved);
        }

        for (FeatureDTO feature : productDTO.getFeatures()) {
            repositoryFeature.findById(feature.getId()).get().getProducts().add(productSaved);
            productSaved.getFeatures().add(repositoryFeature.findById(feature.getId()).get());
        }

        for (ImageDTO image : productDTO.getImages()) {
            Image newImage = new Image();
            newImage.setTitle(image.getTitle());
            newImage.setUrl(image.getUrl());
            newImage.setProduct(productSaved);
            Image imageSaved = repositoryImage.save(newImage);
            productSaved.getImages().add(imageSaved);
        }
        emptyCategory.getProducts().add(savedProduct);
        emptyCategory.setQuantityProducts(repository.countProductByCategoryId(savedProduct.getCategory().getId()));
        repositoryCategory.save(emptyCategory);

        emptyCategory.getProducts().add(savedProduct);
        emptyCategory.setQuantityProducts(repository.countProductByCategoryId(savedProduct.getCategory().getId()));
        repositoryCategory.save(emptyCategory);

        return repository.save(productSaved);
    }

}



