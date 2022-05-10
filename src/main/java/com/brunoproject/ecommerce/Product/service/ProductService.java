package com.brunoproject.ecommerce.Product.service;

import com.brunoproject.ecommerce.ProductUtils.Updater.ProductUpdater;
import com.brunoproject.ecommerce.ProductEntities.ProductDto;
import com.brunoproject.ecommerce.ProductUtils.Mapper.ProductMapper;
import com.brunoproject.ecommerce.ProductDao.ProductRepository;
import com.brunoproject.ecommerce.ProductEntities.Product;
import com.brunoproject.ecommerce.Product.exceptions.ProductListIsEmptyException;
import com.brunoproject.ecommerce.Product.exceptions.ProductNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductUpdater productUpdater;
    private final ProductMapper productMapper;

    public List<ProductDto> getAllProducts() {
        if(productRepository.findAll().isEmpty()) {
            throw new ProductListIsEmptyException();
        }

        return productMapper.convertListOfProducts(productRepository.findAll());
    }

    public List<ProductDto> getAllActiveProducts() {
        var productList = productRepository.findAll()
                .stream()
                .filter(Product::isActive)
                .collect(Collectors.toList());

        if (productList.isEmpty()) {
            throw new ProductListIsEmptyException();
        }

        return productMapper.convertListOfProducts(productList);
    }

    public ProductDto getProduct(Long id) {
        return productMapper.convertSingleProduct(verifyIfProductExists(id));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product informationToUpdate) {
        Product productBeingUpdated = verifyIfProductExists(id);

        return productRepository.save(productUpdater.saveProductWithNewValues(productBeingUpdated, informationToUpdate));
    }

    public Product toggleActivationProduct(Long id) {
        Product productBeingUpdated = verifyIfProductExists(id);

        productBeingUpdated.setActive(!productBeingUpdated.isActive());

        return productRepository.save(productBeingUpdated);
    }

    public void deleteProduct(Long id) {
        var product = verifyIfProductExists(id);

        productRepository.deleteById(product.getId());
    }

    private Product verifyIfProductExists(Long id) {
        return productRepository.findAll().stream()
                .filter(productBeingCompared -> id.equals(productBeingCompared.getId()))
                .findAny()
                .orElseThrow(ProductNotFoundException::new);
    }
}