package com.brunoproject.ecommerce.ProductCategory.exceptions;

public class ProductCategoryIsEmpty extends RuntimeException{
    public ProductCategoryIsEmpty() {
        super("Product Category Is Empty Or Doesnt Exist");
    }
}
