package com.brunoproject.ecommerce.productexceptions;

public class ProductListIsEmptyException extends RuntimeException {

    public ProductListIsEmptyException() {
        super("Product List Is Empty");
    }
}
