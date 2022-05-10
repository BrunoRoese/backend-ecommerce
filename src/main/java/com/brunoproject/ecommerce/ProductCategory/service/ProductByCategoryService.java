package com.brunoproject.ecommerce.ProductCategory.service;

import com.brunoproject.ecommerce.Mapper.ProductMapper;
import com.brunoproject.ecommerce.Product.exceptions.ProductNotFoundException;
import com.brunoproject.ecommerce.ProductCategory.exceptions.ProductCategoryIsEmpty;
import com.brunoproject.ecommerce.ProductCategory.exceptions.ProductCategoryNotFound;
import com.brunoproject.ecommerce.ProductCategory.productCategoryUpdater.ProductCategoryUpdater;
import com.brunoproject.ecommerce.ProductDao.ProductCategoryRepository;
import com.brunoproject.ecommerce.ProductDao.ProductRepository;
import com.brunoproject.ecommerce.ProductEntities.ProductCategory;
import com.brunoproject.ecommerce.ProductEntities.ProductDto;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductByCategoryService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductMapper productMapper;
    private final ProductCategoryUpdater productCategoryUpdater;

    public List<ProductDto> getAllProductByCategory(Long productCategoryId) {
        var productsByCategory = productRepository.findAllByProductCategoryId(productCategoryId);

        if (productsByCategory.isEmpty()) {
            throw new ProductCategoryIsEmpty();
        }

        return productMapper.convertListOfProducts(productsByCategory.get());
    }

    public ProductDto getSpecificProductByCategory(Long productCategoryId, Long productId) {
        var product = productRepository.findProductByIdAndProductCategoryId(productId, productCategoryId);

        if (product.isEmpty()) {
            throw new ProductNotFoundException();
        }

        return productMapper.convertSingleProduct(product.get());
    }

    public ProductCategory createNewProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    public ProductCategory updateProductCategory(ProductCategory productCategory) {
        return productCategoryUpdater.setNewProductCategory(productCategory);
    }

    public void deleteProductCategory(ProductCategory productCategory) {
        productCategoryRepository.delete(productCategory);
    }
}
