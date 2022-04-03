package com.brunoproject.ecommerce.product.productcontroller;

import com.brunoproject.ecommerce.entities.ProductCategory;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    private Long id;
    private ProductCategory productCategory;
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private String imageUrl;
}
