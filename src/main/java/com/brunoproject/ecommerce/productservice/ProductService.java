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
        List<Product> products;

        products = productRepository.findAll();

        return products.stream()
                .filter(product -> id.equals(product.getId()))
                .findAny()
                .orElseThrow(ProductNotFoundException::new);
    }
}