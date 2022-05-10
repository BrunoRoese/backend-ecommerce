package com.brunoproject.ecommerce.ProductCategory.exceptions;

public class ProductCategoryNotFound extends RuntimeException{

    public ProductCategoryNotFound(String message) {
        super(message);
    }
}
