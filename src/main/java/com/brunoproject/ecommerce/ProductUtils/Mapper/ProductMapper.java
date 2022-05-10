package com.brunoproject.ecommerce.ProductUtils.Mapper;

import com.brunoproject.ecommerce.ProductEntities.Product;
import com.brunoproject.ecommerce.ProductEntities.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

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
        productDto.setActive(productDto.isActive());

        return productDto;
    }

    private ProductDto newProductDto() {
        return new ProductDto();
    }
}
