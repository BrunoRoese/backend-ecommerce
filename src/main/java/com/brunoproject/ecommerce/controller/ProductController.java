package com.brunoproject.ecommerce.controller;

import com.brunoproject.ecommerce.entities.Product;
import com.brunoproject.ecommerce.productexceptions.ProductListIsEmptyException;
import com.brunoproject.ecommerce.productservice.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        try {
            return productService.getAllActiveProducts();
        } catch(ProductListIsEmptyException e){
            throw new ProductListIsEmptyException();
        }
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return productService.getProduct(id);
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id) {
        return productService.updateProduct(id);
    }

    @DeleteMapping()
    public void deleteProduct(@RequestBody Long id) {
        productService.deleteProduct(id);
    }
}