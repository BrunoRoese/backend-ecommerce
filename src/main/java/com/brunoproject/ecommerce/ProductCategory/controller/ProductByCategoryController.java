package com.brunoproject.ecommerce.ProductCategory.controller;

import com.brunoproject.ecommerce.ProductCategory.service.ProductByCategoryService;
import com.brunoproject.ecommerce.ProductEntities.ProductCategory;
import com.brunoproject.ecommerce.ProductEntities.ProductDto;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/productCategory")
public class ProductByCategoryController {

    private final ProductByCategoryService productByCategoryService;

    @GetMapping("/{id}")
    public List<ProductDto> listOfProductsByCategory(@PathVariable("id") Long productCategoryId) {
        return productByCategoryService.getAllProductByCategory(productCategoryId);
    }

    @GetMapping("/{productCategoryId}/{productId}")
    public ProductDto getProductByHisId(
            @PathVariable("productCategoryId") Long productCategoryId,
            @PathVariable("productId") Long productId) {
        return productByCategoryService.getSpecificProductByCategory(productId, productCategoryId);
    }

    @PostMapping
    public ProductCategory createNewProductCategory(@RequestBody ProductCategory productCategory) {
        return productByCategoryService.createNewProductCategory(productCategory);
    }

    @PutMapping()
    public ProductCategory updateProductCategory(@RequestBody ProductCategory productCategory) {
        return productByCategoryService.updateProductCategory(productCategory);
    }

    @DeleteMapping
    public void deleteProductCategory(@RequestBody ProductCategory productCategory) {
        productByCategoryService.deleteProductCategory(productCategory);
    }
}
