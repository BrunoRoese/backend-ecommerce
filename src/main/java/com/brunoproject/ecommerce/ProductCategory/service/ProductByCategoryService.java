package com.brunoproject.ecommerce.ProductCategory.service;

import com.brunoproject.ecommerce.Mapper.ProductMapper;
import com.brunoproject.ecommerce.Product.exceptions.ProductNotFoundException;
import com.brunoproject.ecommerce.ProductCategory.exceptions.ProductCategoryIsEmpty;
import com.brunoproject.ecommerce.ProductDao.ProductRepository;
import com.brunoproject.ecommerce.ProductEntities.ProductDto;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductByCategoryService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> getAllProductByCategory(Long productCategoryId) {
        var productsByCategory = productRepository.findAllByProductCategoryId(productCategoryId);

        if(productsByCategory.isEmpty()) {
            throw new ProductCategoryIsEmpty();
        }

        return productMapper.convertListOfProducts(productsByCategory);
    }
}
