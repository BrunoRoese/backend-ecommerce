package com.brunoproject.ecommerce.product.controller;

import com.brunoproject.ecommerce.mapper.ProductMapper;
import com.brunoproject.ecommerce.entities.Product;
import com.brunoproject.ecommerce.entities.ProductDto;
import com.brunoproject.ecommerce.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productMapper.convertListOfProducts(productService.getAllProducts());
    }

    @GetMapping("/active")
    public List<ProductDto> getAllActiveProducts() {
        var products = productService.getAllActiveProducts();

        return productMapper.convertListOfProducts(products);
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable("id") Long id) {
        var product = productService.getProduct(id);

        return productMapper.convertSingleProduct(product);
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @PatchMapping("/{id}")
    public Product toggleActivationProduct(@PathVariable("id") Long id) {
        return productService.toggleActivationProduct(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}