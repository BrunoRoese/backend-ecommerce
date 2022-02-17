package com.brunoproject.ecommerce.productservice;

import com.brunoproject.ecommerce.dao.ProductRepository;
import com.brunoproject.ecommerce.entities.Product;
import com.brunoproject.ecommerce.productexceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        return productRepository.findAll().stream()
                .filter(product -> id.equals(product.getId()))
                .findAny()
                .orElseThrow(ProductNotFoundException::new);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id) {
        Product productBeingUpdated = productRepository.findAll().stream()
                .filter(product -> id.equals(product.getId()))
                .findAny()
                .orElseThrow(ProductNotFoundException::new);

    }

    public void deleteProduct(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException();
        }
    }
}