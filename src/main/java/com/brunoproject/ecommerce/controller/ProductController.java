package com.brunoproject.ecommerce.controller;

import com.brunoproject.ecommerce.entities.Product;
import com.brunoproject.ecommerce.productexceptions.ProductListIsEmptyException;
import com.brunoproject.ecommerce.productservice.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}