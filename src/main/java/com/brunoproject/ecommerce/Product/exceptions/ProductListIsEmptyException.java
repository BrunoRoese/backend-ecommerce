package com.brunoproject.ecommerce.Product.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductListIsEmptyException extends RuntimeException {

    public ProductListIsEmptyException() {
        super("Product List Is Empty");
    }
}
