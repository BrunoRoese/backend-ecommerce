package com.brunoproject.ecommerce.product.productconverter;

import com.brunoproject.ecommerce.product.productcontroller.ProductDto;
import com.brunoproject.ecommerce.entities.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConverter {

    public List<ProductDto> convertListOfProducts(List<Product> productList) {
        return productList.stream()
                .map(this::convertProductDto)
                .collect(Collectors.toList());
    }

    public ProductDto convertSingleProduct(Product product) {
        return convertProductDto(product);
    }

    private ProductDto convertProductDto(Product product) {
        var productDto = newProductDto();

        productDto.setId(product.getId());
        productDto.setProductCategory(product.getProductCategory());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setUnitPrice(product.getUnitPrice());
        productDto.setImageUrl(product.getImageUrl());

        return productDto;
    }

    private ProductDto newProductDto() {
        return new ProductDto();
    }
}
