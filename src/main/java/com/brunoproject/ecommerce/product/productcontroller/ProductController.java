package com.brunoproject.ecommerce.product.productcontroller;

import com.brunoproject.ecommerce.product.productconverter.ProductConverter;
import com.brunoproject.ecommerce.entities.Product;
import com.brunoproject.ecommerce.product.productservice.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping("/active")
    public List<ProductDto> getAllProducts() {
        var products = productService.getAllActiveProducts();

        return productConverter.convertListOfProducts(products);
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable("id") Long id) {
        var product = productService.getProduct(id);

        return productConverter.convertSingleProduct(product);
    }

    @PostMapping()
    public void createProduct(@RequestBody Product product) {
        productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        productService.updateProduct(id, product);
    }

    @PatchMapping("/{id}")
    public void toggleActivationProduct(@PathVariable("id") Long id) {
        productService.toggleActivationProduct(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}