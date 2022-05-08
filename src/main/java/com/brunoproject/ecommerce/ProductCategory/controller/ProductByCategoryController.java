package com.brunoproject.ecommerce.ProductCategory.controller;

import com.brunoproject.ecommerce.ProductCategory.service.ProductByCategoryService;
import com.brunoproject.ecommerce.ProductEntities.Product;
import com.brunoproject.ecommerce.ProductEntities.ProductDto;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
