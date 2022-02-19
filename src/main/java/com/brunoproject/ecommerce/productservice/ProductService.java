package com.brunoproject.ecommerce.productservice;

import com.brunoproject.ecommerce.dao.ProductRepository;
import com.brunoproject.ecommerce.entities.Product;
import com.brunoproject.ecommerce.productexceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllActiveProducts() {
         return productRepository.findAll()
                 .stream()
                 .filter(Product::isActive)
                 .collect(Collectors.toList());
    }

    public Product getProduct(Long id) {
        return verifyIfProductExists(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void updateProduct(Long id, Product informationToUpdate) {
        Product productBeingUpdated = verifyIfProductExists(id);

        productRepository.save(setProductProperties(productBeingUpdated, informationToUpdate));
    }

    public void toggleActivationProduct(Long id) {
        Product productBeingUpdated = verifyIfProductExists(id);

        productBeingUpdated.setActive(!productBeingUpdated.isActive());

        productRepository.save(productBeingUpdated);
    }

    public void deleteProduct(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException();
        }
    }

    private Product verifyIfProductExists(Long id) {
        return productRepository.findAll().stream()
                .filter(productBeingCompared -> id.equals(productBeingCompared.getId()))
                .findAny()
                .orElseThrow(ProductNotFoundException::new);
    }

    private Product setProductProperties(Product productBeingUpdated, Product informationToUpdate) {
        productBeingUpdated.setProductCategory(informationToUpdate.getProductCategory());
        productBeingUpdated.setSku(informationToUpdate.getSku());
        productBeingUpdated.setName(informationToUpdate.getName());
        productBeingUpdated.setDescription(informationToUpdate.getDescription());
        productBeingUpdated.setUnitPrice(informationToUpdate.getUnitPrice());
        productBeingUpdated.setImageUrl(informationToUpdate.getImageUrl());
        productBeingUpdated.setActive(informationToUpdate.isActive());
        productBeingUpdated.setUnitsInStock(informationToUpdate.getUnitsInStock());

        return productBeingUpdated;
    }
}